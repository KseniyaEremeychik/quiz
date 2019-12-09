package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Quiz;
import com.netcracker.edu.backend.repository.QuizRepository;
import com.netcracker.edu.backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuizServiceImpl implements QuizService {
    private QuizRepository quizRepository;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public Iterable<Quiz> findAllQuizByCategoryId(Integer id) {
        return quizRepository.findByCategoryId(id);
    }

    @Override
    public Iterable<Quiz> findAllQuizLike(String searchParam) {
        return quizRepository.findByNameContaining(searchParam);
    }

    @Override
    public Iterable<Quiz> findAllQuizByUserId(Integer id) {
        return quizRepository.findByUserId(id);
    }

    @Override
    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }
}
