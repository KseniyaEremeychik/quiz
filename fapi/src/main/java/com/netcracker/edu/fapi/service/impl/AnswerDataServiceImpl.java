package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Answer;
import com.netcracker.edu.fapi.models.RightAnswerModel;
import com.netcracker.edu.fapi.service.AnswerDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnswerDataServiceImpl implements AnswerDataService {
    @Value("http://localhost:8080/")
    private String backendServerURL;

    @Override
    public RightAnswerModel getRightAnswers(List<Integer> questionsId, List<Integer> userAnswers) {
        RestTemplate restTemplate = new RestTemplate();
        List<Integer> rightAnswers = new ArrayList<>();

        byte isRightByte = 1;
        for(Integer questionId: questionsId) {
            Answer rightAnswer = restTemplate.getForObject(backendServerURL + "api/rightAnswers/?questionId=" + questionId + "&isRight=" + isRightByte, Answer.class);
            rightAnswers.add(rightAnswer.getId());
        }

        Map<Integer, Integer> answers = new HashMap<>();
        List<Boolean> isRight = new ArrayList<>();
        double sum = 0.0;
        for(int i = 0; i < userAnswers.size(); i++) {
            answers.put(userAnswers.get(i), rightAnswers.get(i));
            if(userAnswers.get(i) == rightAnswers.get(i)) {
                sum+=1;
                isRight.add(true);
            } else {
                isRight.add(false);
            }
        }
        double percent = (sum / questionsId.size()) * 100;

        RightAnswerModel rightAnswerModel = new RightAnswerModel(percent, questionsId, answers, isRight);
        return rightAnswerModel;
    }
}
