package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatisticViewModel {
    private int userId;
    private String userName;
    private int quizId;
    private String quizName;
    private double rightAnswersPercent;
    private String passageDate;

    public StatisticViewModel() {
    }

    public StatisticViewModel(int userId, int quizId, double rightAnswersPercent, String passageDate) {
        this.userId = userId;
        this.quizId = quizId;
        this.rightAnswersPercent = rightAnswersPercent;
        this.passageDate = passageDate;
    }

    public StatisticViewModel(int userId, int quizId, String quizName, double rightAnswersPercent, String passageDate) {
        this.userId = userId;
        this.quizId = quizId;
        this.quizName = quizName;
        this.rightAnswersPercent = rightAnswersPercent;
        this.passageDate = passageDate;
    }

    public StatisticViewModel(int userId, String userName, int quizId, String quizName, double rightAnswersPercent, String passageDate) {
        this.userId = userId;
        this.userName = userName;
        this.quizId = quizId;
        this.quizName = quizName;
        this.rightAnswersPercent = rightAnswersPercent;
        this.passageDate = passageDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public double getRightAnswersPercent() {
        return rightAnswersPercent;
    }

    public void setRightAnswersPercent(double rightAnswersPercent) {
        this.rightAnswersPercent = rightAnswersPercent;
    }

    public String getPassageDate() {
        return passageDate;
    }

    public void setPassageDate(String passageDate) {
        this.passageDate = passageDate;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
