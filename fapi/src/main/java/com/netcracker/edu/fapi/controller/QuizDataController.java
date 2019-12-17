package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.QuestionViewModel;
import com.netcracker.edu.fapi.models.QuizViewModel;
import com.netcracker.edu.fapi.models.QuizWithQuestionsModel;
import com.netcracker.edu.fapi.service.QuizDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class QuizDataController {
    @Autowired
    private QuizDataService quizDataService;

    @RequestMapping("/api/quiz")
    public ResponseEntity<List<QuestionViewModel>> getAllQuestionsByQuizId(@RequestParam(name = "quizId") String id) {
        return ResponseEntity.ok(quizDataService.getAllQuestionsByQuizId(Integer.valueOf(id)));
    }

    @RequestMapping("/api/quizLike")
    public Page<QuizViewModel> findAllQuizLike(@RequestParam(name = "searchParam") String searchParam,
                                               @RequestParam(name = "page") Integer page,
                                               @RequestParam(name = "size") Integer size) {
        return quizDataService.findAllQuizLike(searchParam, page, size);
    }

    @RequestMapping("/api/allUsersQuiz")
    public ResponseEntity<List<QuizViewModel>> findAllQuizByUserId(@RequestParam(name = "userId") String id) {
        return ResponseEntity.ok(quizDataService.findAllQuizByUserId(Integer.valueOf(id)));
    }

    @RequestMapping(value = "/api/newQuiz", method = RequestMethod.POST)
    public ResponseEntity<QuizWithQuestionsModel> saveNewQuiz(@RequestBody @Valid QuizWithQuestionsModel newQuiz) {
        if(newQuiz != null) {
            if (quizDataService.saveNewQuiz(newQuiz) != null) {
                return ResponseEntity.ok(quizDataService.saveNewQuiz(newQuiz));
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return null;
    }

    @RequestMapping(value = "/api/quizDel/{quizId}", method = RequestMethod.DELETE)
    public void deleteQuizById(@PathVariable(name = "quizId") String quizId) {
        quizDataService.deleteQuizById(Integer.valueOf(quizId));
    }

    @RequestMapping(value = "/api/quizByPage", method = RequestMethod.GET)
    public Page<QuizViewModel> getQuizByPage(@RequestParam(name = "categoryId") Integer categoryId,
                                             @RequestParam(name = "page") Integer page,
                                             @RequestParam(name = "size") Integer size) {
        return quizDataService.getQuizByPage(categoryId, page, size);
    }

    @RequestMapping(value = "/api/quizByPageAndStatus", method = RequestMethod.GET)
    public Page<QuizViewModel> getQuizByPageAndStatus(@RequestParam(name = "categoryId") Integer categoryId,
                                                      @RequestParam(name = "page") Integer page,
                                                      @RequestParam(name = "size") Integer size,
                                                      @RequestParam(name = "status") String status) {
        return quizDataService.getQuizByPageAndStatus(categoryId, page, size, status);
    }

    @RequestMapping("/api/allQuiz")
    public Page<QuizViewModel>  getAllQuiz(@RequestParam(name = "page") Integer page,
                                           @RequestParam(name = "size") Integer size) {
        return quizDataService.getAllQuiz(page, size);
    }

    @RequestMapping("/api/allQuizWithStatus")
    public Page<QuizViewModel>  getAllQuizWithStatus(@RequestParam(name = "page") Integer page,
                                                     @RequestParam(name = "size") Integer size,
                                                     @RequestParam(name = "status") String status) {
        return quizDataService.getAllQuizWithStatus(page, size, status);
    }
}
