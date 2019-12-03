package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserDataController {
    @Autowired
    private UserDataService userDataService;

    @RequestMapping(value = "/api/user", method = RequestMethod.POST)
    public ResponseEntity<UserViewModel> saveUser(@RequestBody @Valid UserViewModel user) {
        if(user != null) {
            return ResponseEntity.ok(userDataService.saveUser(user));
        }
        return null;
    }

    /*@RequestMapping(value = "/api/user", method = RequestMethod.POST)
    public ResponseEntity<UserViewModel> saveUser(@RequestBody UserViewModel user) {
        if(user != null) {
            return ResponseEntity.ok(userDataService.saveUser(user));
        }
        return null;
    }*/

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
    public UserViewModel findUserByEmail(@RequestBody UserViewModel userLogin) {
        return userDataService.findByEmail(userLogin.getEmail(), userLogin.getPassword());
    }

    @RequestMapping(value="/api/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String id) {
        userDataService.deleteUser(Integer.valueOf(id));
    }
}
