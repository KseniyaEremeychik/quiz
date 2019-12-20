package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.RatingViewModel;
import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.RatingDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

@Service
public class RatingDataServiceImpl implements RatingDataService {
    @Value("http://localhost:8080/")
    private String backendServerURL;

    @Override
    public List<RatingViewModel> getTopTen() {
        RestTemplate restTemplate = new RestTemplate();
        RatingViewModel[] ratingViewResponse = restTemplate.getForObject(backendServerURL + "api/topTen", RatingViewModel[].class);


        for (RatingViewModel rating : ratingViewResponse) {
            RestTemplate restTemplate1 = new RestTemplate();
            UserViewModel user = restTemplate1.getForObject(backendServerURL + "api/userBe/?userId=" + rating.getUserId(), UserViewModel.class);
            rating.setUserName(user.getUserName());
        }

        return ratingViewResponse == null ? Collections.emptyList() : Arrays.asList(ratingViewResponse);
    }
}
