package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Quiz;
import com.netcracker.edu.backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quizBe")
public class QuizController {
    private QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Quiz> findAllQuizByCategoryId(@RequestParam(name = "categoryId") Integer id) {
        return this.quizService.findAllQuizByCategoryId(id);
    }
}