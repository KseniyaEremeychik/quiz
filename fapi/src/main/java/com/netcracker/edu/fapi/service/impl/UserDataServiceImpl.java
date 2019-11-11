package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    public UserViewModel findByEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        UserViewModel user = restTemplate.getForObject(backendServerURL + "/api/users/?email=" + email, UserViewModel.class);
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
        restTemplate.delete(backendServerURL + "api/users/" + id);
    }
}
