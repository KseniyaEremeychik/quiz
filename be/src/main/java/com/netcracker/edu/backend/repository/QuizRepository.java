package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Quiz;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends PagingAndSortingRepository<Quiz, Integer> {
    Iterable<Quiz> findByCategoryId(Integer id);
}
