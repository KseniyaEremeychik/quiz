package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Rating;

public interface RatingService {
    Rating getRatingByUserId(Integer userId);
    Rating saveRating(Rating rating);
}
