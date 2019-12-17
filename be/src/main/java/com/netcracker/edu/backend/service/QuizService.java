package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Quiz;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface QuizService {
    Iterable<Quiz> findAllQuizByCategoryId(Integer id);
    //Iterable<Quiz> findAllQuizLike(String searchParam);
    //Page<Quiz> findAllQuizLike(String searchParam, Integer page, Integer size);
    Page<Quiz> findAllQuizLike(String searchParam, Integer page, Integer size);
    Iterable<Quiz> findAllQuizByUserId(Integer id);
    Quiz saveQuiz(Quiz quiz);
    void deleteQuizById(Integer quizId);
    Optional<Quiz> getQuizById(Integer id);
    Page<Quiz> getQuizByPage(Integer categoryId, Integer page, Integer size);
    Page<Quiz> getQuizByPageAndStatus(String status, Integer categoryId, Integer page, Integer size);
    Page<Quiz> getAll(Integer page, Integer size);
    Page<Quiz> getAllQuizWithStatus(Integer page, Integer size, String status);
}
