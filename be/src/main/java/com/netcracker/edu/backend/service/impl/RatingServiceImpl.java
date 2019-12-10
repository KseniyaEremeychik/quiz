package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Rating;
import com.netcracker.edu.backend.repository.RatingRepository;
import com.netcracker.edu.backend.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RatingServiceImpl implements RatingService {
    private RatingRepository ratingRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating getRatingByUserId(Integer userId) {
        return this.ratingRepository.findByUserId(userId);
    }

    @Override
    public Rating saveRating(Rating rating) {
        return this.ratingRepository.save(rating);
    }
}
