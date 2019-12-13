package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Rating;
import com.netcracker.edu.backend.repository.RatingRepository;
import com.netcracker.edu.backend.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Override
    public Iterable<Rating> getTopTen() {
        return this.ratingRepository.findAll(PageRequest.of(0, 10, Sort.by("percent").descending())).getContent();
    }
}
