package com.netcracker.edu.fapi.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.edu.fapi.models.QuizViewModel;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class QuizConverter {
    @Autowired
    private UserDataService userDataService;

    public QuizViewModel addUserNameToQuiz(QuizViewModel quiz) {
        quiz.setUserName(userDataService.getUserById(quiz.getUserId()).getUserName());
        return quiz;
    }

    public Function<List<QuizViewModel>, List<QuizViewModel>> collectionTransform = sourceList -> {
        ObjectMapper m = new ObjectMapper();
        List<QuizViewModel> quizList = new ArrayList<>();
        for (int i = 0; i < sourceList.size(); i++) {
            quizList.add(addUserNameToQuiz(m.convertValue(sourceList.get(i), QuizViewModel.class)));
        }
        return quizList;
    };
}
