package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Answer;

public interface AnswerService {
    Iterable<Answer> findAllAnswersByQuestionId(Integer id);
}
