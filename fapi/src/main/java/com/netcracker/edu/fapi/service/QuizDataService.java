package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.QuestionViewModel;
import com.netcracker.edu.fapi.models.QuizViewModel;

import java.util.List;

public interface QuizDataService {
    List<QuizViewModel> findAllQuizByCategoryId(Integer id);
    List<QuestionViewModel> getAllQuestionsByQuizId(Integer id);
    List<QuizViewModel> findAllQuizLike(String searchParam);
}
