package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Value;
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
        return restTemplate.postForEntity(backendServerURL + "/api/users", user, UserViewModel.class).getBody();
    }

    @Override
    public UserViewModel findByEmail(String email, String password) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> err = new HashMap<>();
        UserViewModel user = restTemplate.getForObject(backendServerURL + "/api/users/?email=" + email, UserViewModel.class);
        if(user != null) {
            if(!user.getPassword().equals(password)) {
                user = new UserViewModel();
                err.put("password", "Incorrect password");
            }
        } else {
            user = new UserViewModel();
            err.put("email", "Incorrect email");
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
