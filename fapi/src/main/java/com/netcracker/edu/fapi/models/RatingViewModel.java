package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RatingViewModel {
    private int id;
    private int userId;
    private String userName;
    private double percent;
    private int quizNum;

    public RatingViewModel() {
    }

    public RatingViewModel(int id, int userId, String userName, double percent, int quizNum) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.percent = percent;
        this.quizNum = quizNum;
    }

    public RatingViewModel(int id, int userId, double percent, int quizNum) {
        this.id = id;
        this.userId = userId;
        this.percent = percent;
        this.quizNum = quizNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public int getQuizNum() {
        return quizNum;
    }

    public void setQuizNum(int quizNum) {
        this.quizNum = quizNum;
    }
}
