package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Quiz;

public interface QuizService {
    Iterable<Quiz> findAllQuizByCategoryId(Integer id);
    Iterable<Quiz> findAllQuizLike(String searchParam);
    Iterable<Quiz> findAllQuizByUserId(Integer id);
}
