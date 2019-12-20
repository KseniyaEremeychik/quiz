package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Question;
import com.netcracker.edu.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuestionController {
    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(value = "/api/questionsBe", method = RequestMethod.GET)
    public Iterable<Question> getAllQuestionsByQuizId(@RequestParam(name = "quizId") Integer id) {
        return this.questionService.findAllQuestionsByQuizId(id);
    }

    @RequestMapping(value = "api/saveQuestion", method = RequestMethod.POST)
    public Question saveQuestionInQuiz(@RequestBody Question question) {
        return this.questionService.saveQuestion(question);
    }
}
