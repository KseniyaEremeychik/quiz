package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends PagingAndSortingRepository<Rating, Integer> {
    Rating findByUserId(Integer userId);
}
