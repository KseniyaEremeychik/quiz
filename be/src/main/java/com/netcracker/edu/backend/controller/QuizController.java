package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Question;
import com.netcracker.edu.backend.entity.Quiz;
import com.netcracker.edu.backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {
    private QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @RequestMapping(value = "/api/quizBe",  method = RequestMethod.GET)
    public Iterable<Quiz> findAllQuizByCategoryId(@RequestParam(name = "categoryId") Integer id) {
        return this.quizService.findAllQuizByCategoryId(id);
    }

    @RequestMapping(value = "/api/quizLike", method = RequestMethod.GET)
    public Iterable<Quiz> findAllQuizLike(@RequestParam(name = "searchParam") String searchParam) {
        return this.quizService.findAllQuizLike(searchParam);
    }

    @RequestMapping(value = "api/quizByUser", method = RequestMethod.GET)
    public Iterable<Quiz> findAllQuizByUserId(@RequestParam(name = "userId") Integer id) {
        return this.quizService.findAllQuizByUserId(id);
    }

    @RequestMapping(value = "api/newQuiz", method = RequestMethod.POST)
    public Quiz saveQuiz(@RequestBody Quiz quiz) {
        return this.quizService.saveQuiz(quiz);
    }
}
