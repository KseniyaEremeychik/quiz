package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Question;
import com.netcracker.edu.backend.repository.QuestionRepository;
import com.netcracker.edu.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionServiceImpl implements QuestionService {
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Iterable<Question> findAllQuestionsByQuizId(Integer id) {
        return this.questionRepository.findByQuizId(id);
    }

    @Override
    public Question saveQuestion(Question question) {
        return this.questionRepository.save(question);
    }
}
