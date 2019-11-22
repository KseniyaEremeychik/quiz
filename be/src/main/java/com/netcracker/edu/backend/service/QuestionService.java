package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Question;

public interface QuestionService {
    Iterable<Question> findAllQuestionsByQuizId(Integer id);
}
