package com.netcracker.edu.fapi.service.impl;

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
}
