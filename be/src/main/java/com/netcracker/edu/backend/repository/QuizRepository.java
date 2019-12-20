package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends PagingAndSortingRepository<Quiz, Integer> {
    Iterable<Quiz> findByCategoryId(Integer id);

    Iterable<Quiz> findByUserId(Integer id);

    Page<Quiz> findByCategoryId(Integer categoryId, Pageable pageable);

    Page<Quiz> findByCategoryIdAndIsConfirmed(Integer categoryId, Quiz.Confirmation isConfirmed, Pageable pageable);

    Page<Quiz> findByIsConfirmedAndNameContaining(Quiz.Confirmation isConfirmed, String searchParam, Pageable pageable);

    Page<Quiz> findByIsConfirmed(Quiz.Confirmation isConfirmed, Pageable pageable);
}
