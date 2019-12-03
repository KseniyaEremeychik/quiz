package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.RightAnswerModel;

import java.util.List;
import java.util.Map;

public interface AnswerDataService {
    public RightAnswerModel getRightAnswers(List<Integer> questionsId, List<Integer> userAnswers);
}
