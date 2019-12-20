package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Question;
import com.netcracker.edu.backend.entity.Quiz;
import com.netcracker.edu.backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class QuizController {
    private QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @RequestMapping(value = "/api/quizBe", method = RequestMethod.GET)
    public Iterable<Quiz> findAllQuizByCategoryId(@RequestParam(name = "categoryId") Integer id) {
        return this.quizService.findAllQuizByCategoryId(id);
    }

    @RequestMapping(value = "/api/quizLike", method = RequestMethod.GET)
    public Page<Quiz> findAllQuizLike(@RequestParam(name = "searchParam") String searchParam,
                                      @RequestParam(name = "page") Integer page,
                                      @RequestParam(name = "size") Integer size) {
        return this.quizService.findAllQuizLike(searchParam, page, size);
    }

    @RequestMapping(value = "api/quizByUser", method = RequestMethod.GET)
    public Iterable<Quiz> findAllQuizByUserId(@RequestParam(name = "userId") Integer id) {
        return this.quizService.findAllQuizByUserId(id);
    }

    @RequestMapping(value = "api/newQuiz", method = RequestMethod.POST)
    public Quiz saveQuiz(@RequestBody Quiz quiz) {
        return this.quizService.saveQuiz(quiz);
    }

    @RequestMapping(value = "/api/deleteQuizBe/{quizId}", method = RequestMethod.DELETE)
    public void deleteQuizById(@PathVariable(name = "quizId") Integer quizId) {
        quizService.deleteQuizById(quizId);
    }

    @RequestMapping(value = "api/quizById", method = RequestMethod.GET)
    public ResponseEntity<Quiz> getQuizById(@RequestParam(name = "quizId") Integer id) {
        Optional<Quiz> quiz = quizService.getQuizById(id);
        if (quiz.isPresent()) {
            return ResponseEntity.ok(quiz.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "api/quizPage", method = RequestMethod.GET)
    public Page<Quiz> getQuizByPage(@RequestParam(name = "categoryId") Integer categoryId,
                                    @RequestParam(name = "page") Integer page,
                                    @RequestParam(name = "size") Integer size) {
        return quizService.getQuizByPage(categoryId, page, size);
    }

    @RequestMapping(value = "api/quizPageAndStatus", method = RequestMethod.GET)
    public Page<Quiz> getQuizByPageAndStatus(@RequestParam(name = "categoryId") Integer categoryId,
                                             @RequestParam(name = "page") Integer page,
                                             @RequestParam(name = "size") Integer size,
                                             @RequestParam(name = "status") String status) {
        return quizService.getQuizByPageAndStatus(status, categoryId, page, size);
    }

    @RequestMapping(value = "api/allQuizBe", method = RequestMethod.GET)
    public Page<Quiz> getAll(@RequestParam(name = "page") Integer page,
                             @RequestParam(name = "size") Integer size,
                             @RequestParam(name = "sortParam", required = false) String sortParam,
                             @RequestParam(name = "sortFormat", required = false) Integer sortFormat) {
        return quizService.getAll(page, size, sortParam, sortFormat);
    }

    @RequestMapping(value = "api/allQuizWithStatus", method = RequestMethod.GET)
    public Page<Quiz> getAllQuizWithStatus(@RequestParam(name = "page") Integer page,
                                           @RequestParam(name = "size") Integer size,
                                           @RequestParam(name = "status") String status,
                                           @RequestParam(name = "sortParam", required = false) String sortParam,
                                           @RequestParam(name = "sortFormat", required = false) Integer sortFormat) {
        return quizService.getAllQuizWithStatus(page, size, status, sortParam, sortFormat);
    }
}
