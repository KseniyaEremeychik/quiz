package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.models.UserWithAuthToken;
import com.netcracker.edu.fapi.security.JwtTokenProvider;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class UserDataController {
    @Autowired
    private UserDataService userDataService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @RequestMapping(value = "/api/user", method = RequestMethod.POST)
    public ResponseEntity<UserViewModel> saveUser(@RequestBody @Valid UserViewModel user) {
        if (user != null) {
            return ResponseEntity.ok(userDataService.saveUser(user));
        }
        return null;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserViewModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        UserViewModel user = new UserViewModel();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        user.setErrors(errors);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/api/userLogin", method = RequestMethod.POST)
    public UserWithAuthToken findUserByEmail(@RequestBody UserViewModel userLogin) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getEmail(),
                        userLogin.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);
        UserViewModel signedUser = userDataService.findByEmail(userLogin.getEmail());
        signedUser.setPassword(null);

        return new UserWithAuthToken(signedUser, token);
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String id) {
        userDataService.deleteUser(Integer.valueOf(id));
    }

    @RequestMapping(value = "/api/userLoginByToken", method = RequestMethod.POST)
    public UserViewModel findUserByToken(@RequestBody String token) {
        String emailByToken = tokenProvider.getUsernameFromToken(token);
        return userDataService.getUserByToken(emailByToken);
    }
}
