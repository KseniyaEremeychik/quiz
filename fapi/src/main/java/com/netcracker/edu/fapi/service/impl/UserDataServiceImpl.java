package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service("customUserDetailsService")
public class UserDataServiceImpl implements UserDataService, UserDetailsService {
    @Value("http://localhost:8080/")
    private String backendServerURL;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserViewModel saveUser(UserViewModel user) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> err = new HashMap();
        Boolean isUserNameExist = restTemplate.getForObject(backendServerURL + "/api/userCheckName/?userName=" + user.getUserName(), Boolean.class);
        Boolean isEmailExist = restTemplate.getForObject(backendServerURL + "/api/usersCheckEmail/?email=" + user.getEmail(), Boolean.class);
        if (isUserNameExist) {
            user = new UserViewModel();
            err.put("userName", "This username already exists");
        }

        if (isEmailExist) {
            user = new UserViewModel();
            err.put("email", "You are already registered");
        }

        if (err.size() == 0) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            UserViewModel newUser = restTemplate.postForEntity(backendServerURL + "/api/users", user, UserViewModel.class).getBody();
            newUser.setErrors(null);
            return newUser;
        } else {
            user.setErrors(err);
            return user;
        }
    }

    @Override
    public UserViewModel findByEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> err = new HashMap<>();
        UserViewModel user = restTemplate.getForObject(backendServerURL + "/api/users/?email=" + email, UserViewModel.class);
        if (user != null) {
            err = null;
        } else {
            user = new UserViewModel();
            err.put("email", "Email not found");
        }
        user.setErrors(err);
        return user;
    }

    @Override
    public UserViewModel getUserByToken(String emailByToken) {
        RestTemplate restTemplate = new RestTemplate();
        UserViewModel user = restTemplate.getForObject(backendServerURL + "/api/users/?email=" + emailByToken, UserViewModel.class);
        user.setPassword(null);
        return user;
    }

    @Override
    public Page<UserViewModel> getAll(Integer page, Integer size, String sortParam, Integer sortFormat) {
        RestTemplate restTemplate = new RestTemplate();
        Page<UserViewModel> users = null;

        if (sortParam == null) {
            users = restTemplate.getForObject(backendServerURL + "api/usersEditing?page=" + page + "&size=" + size, RestPageImpl.class);
        } else {
            users = restTemplate.getForObject(backendServerURL + "api/usersEditing?page=" + page + "&size=" + size + "&sortParam=" + sortParam + "&sortFormat=" + sortFormat, RestPageImpl.class);
        }

        return users;
    }

    @Override
    public void deleteUser(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerURL + "/api/users/" + id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserViewModel user = findByEmail(email);
        if (user.getErrors() != null) {
            throw new UsernameNotFoundException("User with email: " + email + " not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserViewModel user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }

    @Override
    public UserViewModel getUserById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerURL + "api/userBe/?userId=" + id, UserViewModel.class);
    }
}
