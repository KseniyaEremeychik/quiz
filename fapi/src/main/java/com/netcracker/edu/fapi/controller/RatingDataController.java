package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.RatingViewModel;
import com.netcracker.edu.fapi.service.RatingDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RatingDataController {
    @Autowired
    private RatingDataService ratingDataService;

    @RequestMapping(value = "/api/getTop")
    public List<RatingViewModel> getTopTen() {
        return ratingDataService.getTopTen();
    }
}
