package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class UserDataServiceImpl implements UserDataService {
    @Value("http://localhost:8080/")
    private String backendServerURL;

    @Override
    public UserViewModel saveUser(UserViewModel user) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> err = new HashMap();
        Boolean isUserNameExist = restTemplate.getForObject(backendServerURL + "/api/userCheckName/?userName=" + user.getUserName(), Boolean.class);
        Boolean isEmailExist = restTemplate.getForObject(backendServerURL + "/api/usersCheckEmail/?email=" + user.getEmail(), Boolean.class);;
        if(isUserNameExist) {
            user = new UserViewModel();
            err.put("userName", "This username already exists");
        }

        if(isEmailExist) {
            user = new UserViewModel();
            err.put("email", "You are already registered");
        }

        if(err.size() == 0) {
            UserViewModel newUser = restTemplate.postForEntity(backendServerURL + "/api/users", user, UserViewModel.class).getBody();
            newUser.setErrors(null);
            return newUser;
        } else {
            user.setErrors(err);
            return user;
        }
    }

    @Override
    public UserViewModel findByEmail(String email, String password) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> err = new HashMap<>();
        UserViewModel user = restTemplate.getForObject(backendServerURL + "/api/users/?email=" + email, UserViewModel.class);
        if(user != null) {
            if(!user.getPassword().equals(password)) {
                user = new UserViewModel();
                err.put("password", "Password is incorrect");
            } else {
                err = null;
            }
        } else {
            user = new UserViewModel();
            err.put("email", "Email not found");
        }
        user.setErrors(err);
        return user;
    }

    @Override
    public List<UserViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        UserViewModel[] userViewModelsResponse = restTemplate.getForObject(backendServerURL + "/api/usersEditing", UserViewModel[].class);
        return userViewModelsResponse == null ? Collections.emptyList() : Arrays.asList(userViewModelsResponse);
    }

    @Override
    public void deleteUser(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerURL + "/api/users/" + id);
    }
}
