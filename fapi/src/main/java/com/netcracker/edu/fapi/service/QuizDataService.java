package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.QuestionViewModel;
import com.netcracker.edu.fapi.models.QuizViewModel;
import com.netcracker.edu.fapi.models.QuizWithQuestionsModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuizDataService {
    //List<QuizViewModel> findAllQuizByCategoryId(Integer id);
    List<QuestionViewModel> getAllQuestionsByQuizId(Integer id);
    //List<QuizViewModel> findAllQuizLike(String searchParam);
    Page<QuizViewModel> findAllQuizLike(String searchParam, Integer page, Integer size);
    List<QuizViewModel> findAllQuizByUserId(Integer id);
    QuizWithQuestionsModel saveNewQuiz(QuizWithQuestionsModel newQuiz);
    void deleteQuizById(Integer quizId);
    Page<QuizViewModel> getQuizByPage(Integer categoryId, Integer page, Integer size);
    Page<QuizViewModel> getQuizByPageAndStatus(Integer categoryId, Integer page, Integer size, String status);
    Page<QuizViewModel> getAllQuiz(Integer page, Integer size);
    Page<QuizViewModel> getAllQuizWithStatus(Integer page, Integer size, String status);
}
