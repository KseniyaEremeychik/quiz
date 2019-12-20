package com.netcracker.edu.fapi.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.edu.fapi.models.QuizViewModel;
import com.netcracker.edu.fapi.models.StatisticViewModel;
import com.netcracker.edu.fapi.service.QuizDataService;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class StatisticConverter {
    @Autowired
    private UserDataService userDataService;

    @Autowired
    private QuizDataService quizDataService;

    public StatisticViewModel addUserAndQuizNamesToQuiz(StatisticViewModel stat) {
        stat.setUserName(userDataService.getUserById(stat.getUserId()).getUserName());
        stat.setQuizName(quizDataService.getQuizById(stat.getQuizId()).getName());
        return stat;
    }

    public Function<List<StatisticViewModel>, List<StatisticViewModel>> collectionTransform = sourceList -> {
        ObjectMapper m = new ObjectMapper();
        List<StatisticViewModel> statList = new ArrayList<>();
        for (int i = 0; i < sourceList.size(); i++) {
            statList.add(addUserAndQuizNamesToQuiz(m.convertValue(sourceList.get(i), StatisticViewModel.class)));
        }
        return statList;
    };
}
