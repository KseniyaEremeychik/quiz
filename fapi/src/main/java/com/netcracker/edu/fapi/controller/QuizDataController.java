package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.QuestionViewModel;
import com.netcracker.edu.fapi.models.QuizViewModel;
import com.netcracker.edu.fapi.models.QuizWithQuestionsModel;
import com.netcracker.edu.fapi.service.QuizDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuizDataController {
    @Autowired
    private QuizDataService quizDataService;

    @RequestMapping("/api/allQuiz")
    public ResponseEntity<List<QuizViewModel>> findAllQuizByCategoryId(@RequestParam(name = "categoryId") String id) {
        return ResponseEntity.ok(quizDataService.findAllQuizByCategoryId(Integer.valueOf(id)));
    }

    @RequestMapping("/api/quiz")
    public ResponseEntity<List<QuestionViewModel>> getAllQuestionsByQuizId(@RequestParam(name = "quizId") String id) {
        return ResponseEntity.ok(quizDataService.getAllQuestionsByQuizId(Integer.valueOf(id)));
    }

    @RequestMapping("/api/quizLike")
    public ResponseEntity<List<QuizViewModel>> findAllQuizLike(@RequestParam(name = "searchParam") String searchParam) {
        return ResponseEntity.ok(quizDataService.findAllQuizLike(searchParam));
    }

    @RequestMapping("/api/allUsersQuiz")
    public ResponseEntity<List<QuizViewModel>> findAllQuizByUserId(@RequestParam(name = "userId") String id) {
        return ResponseEntity.ok(quizDataService.findAllQuizByUserId(Integer.valueOf(id)));
    }

    @RequestMapping(value = "/api/newQuiz", method = RequestMethod.POST)
    public ResponseEntity<QuizWithQuestionsModel> saveNewQuiz(@RequestBody QuizWithQuestionsModel newQuiz) {
        if(newQuiz != null) {
            return ResponseEntity.ok(quizDataService.saveNewQuiz(newQuiz));
        }
        return null;
    }

    @RequestMapping(value = "/api/quizDel/{quizId}", method = RequestMethod.DELETE)
    public void deleteQuizById(@PathVariable(name = "quizId") String quizId) {
        quizDataService.deleteQuizById(Integer.valueOf(quizId));
    }
}
