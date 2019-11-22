package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.QuestionViewModel;
import com.netcracker.edu.fapi.models.QuizViewModel;
import com.netcracker.edu.fapi.service.QuizDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuizDataController {
    @Autowired
    private QuizDataService quizDataService;

    //todo check field isConfirm (if not approved, do not return to frontend)
    @RequestMapping("/api/allQuiz")
    public ResponseEntity<List<QuizViewModel>> findAllQuizByCategoryId(@RequestParam(name = "categoryId") String id) {
        return ResponseEntity.ok(quizDataService.findAllQuizByCategoryId(Integer.valueOf(id)));
    }

    @RequestMapping("/api/quiz")
    public ResponseEntity<List<QuestionViewModel>> getAllQuestionsByQuizId(@RequestParam(name = "quizId") String id) {
        return ResponseEntity.ok(quizDataService.getAllQuestionsByQuizId(Integer.valueOf(id)));
    }
}
