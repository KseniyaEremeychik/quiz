package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Answer;
import com.netcracker.edu.backend.repository.AnswerRepository;
import com.netcracker.edu.backend.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnswerServiceImpl implements AnswerService {
    private AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Iterable<Answer> findAllAnswersByQuestionId(Integer id) {
        return this.answerRepository.findByQuestionId(id);
    }
}
