package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserDataServiceImpl implements UserDataService {
    @Value("http://localhost:8080/")
    private String backendServerURL;

    @Override
    public UserViewModel saveUser(UserViewModel user) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerURL + "/api/users", user, UserViewModel.class).getBody();
    }
}
