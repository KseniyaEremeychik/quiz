package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Answer;
import com.netcracker.edu.backend.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnswerController {
    private AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @RequestMapping(value = "/api/answersBe")
    public Iterable<Answer> findAllAnswersByQuestionId(@RequestParam(name = "questionId") Integer id) {
        return answerService.findAllAnswersByQuestionId(id);
    }

    @RequestMapping(value = "/api/rightAnswers")
    public Answer getRightAnswers(@RequestParam(name = "questionId") Integer questionId, @RequestParam(name = "isRight") byte isRight) {
        return answerService.getRightAnswers(questionId, isRight);
    }
}
