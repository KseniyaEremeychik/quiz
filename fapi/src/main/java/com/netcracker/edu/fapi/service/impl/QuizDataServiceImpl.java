package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Answer;
import com.netcracker.edu.fapi.models.QuestionViewModel;
import com.netcracker.edu.fapi.models.QuizViewModel;
import com.netcracker.edu.fapi.service.QuizDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class QuizDataServiceImpl implements QuizDataService {
    @Value("http://localhost:8080/")
    private String backendServerURL;

    @Override
    public List<QuizViewModel> findAllQuizByCategoryId(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        QuizViewModel[] quizViewModelsResponse = restTemplate.getForObject(backendServerURL + "/api/quizBe/?categoryId=" + id, QuizViewModel[].class);
        return quizViewModelsResponse == null ? Collections.emptyList() : Arrays.asList(quizViewModelsResponse);
    }

    @Override
    public List<QuestionViewModel> getAllQuestionsByQuizId(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        QuestionViewModel[] questionViewModels = restTemplate.getForObject(backendServerURL + "api/questionsBe/?quizId=" + id, QuestionViewModel[].class);

        RestTemplate restTemplate1 = new RestTemplate();
        for(int i=0; i<questionViewModels.length; i++) {
            Answer[] answers = restTemplate1.getForObject(backendServerURL + "api/answersBe/?questionId=" + questionViewModels[i].getId(), Answer[].class);
            questionViewModels[i].setAnswers(Arrays.asList(answers));
        }
        return questionViewModels == null ? Collections.emptyList() : Arrays.asList(questionViewModels);
    }
}
