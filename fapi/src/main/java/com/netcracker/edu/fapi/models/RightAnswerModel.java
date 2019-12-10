package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RightAnswerModel {
    private double percent;
    private List<Integer> questionsId;
    private List<Boolean> isRight;

    public RightAnswerModel() {
    }

    public RightAnswerModel(double percent, List<Integer> questionsId, List<Boolean> isRight) {
        this.percent = percent;
        this.questionsId = questionsId;
        this.isRight = isRight;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public List<Integer> getQuestionsId() {
        return questionsId;
    }

    public void setQuestionsId(List<Integer> questionsId) {
        this.questionsId = questionsId;
    }

    public List<Boolean> getIsRight() {
        return isRight;
    }

    public void setIsRight(List<Boolean> isRight) {
        this.isRight = isRight;
    }
}
