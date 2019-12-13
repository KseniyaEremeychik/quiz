package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Rating;
import com.netcracker.edu.backend.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RatingController {
    private RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @RequestMapping(value = "api/userRating", method = RequestMethod.GET)
    public Rating getRatingByUserId(@RequestParam(name = "userId") Integer userId) {
        return this.ratingService.getRatingByUserId(userId);
    }

    @RequestMapping(value = "api/saveRating", method = RequestMethod.POST)
    public Rating saveRating(@RequestBody Rating rating) {
        return this.ratingService.saveRating(rating);
    }

    @RequestMapping(value = "api/topTen")
    public Iterable<Rating> getTopTen() {
        return this.ratingService.getTopTen();
    }
}
