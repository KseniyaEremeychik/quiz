package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Quiz;

import java.util.Optional;

public interface QuizService {
    Iterable<Quiz> findAllQuizByCategoryId(Integer id);
    Iterable<Quiz> findAllQuizLike(String searchParam);
    Iterable<Quiz> findAllQuizByUserId(Integer id);
    Quiz saveQuiz(Quiz quiz);
    void deleteQuizById(Integer quizId);
    Optional<Quiz> getQuizById(Integer id);
}
