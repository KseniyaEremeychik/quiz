package com.netcracker.edu.fapi.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuizViewModel {
    private int id;
    private int categoryId;
    private String name;
    private int questionNumber;
    private Integer time;
    private String isConfirmed;
    //private Date creationDate;
    private String creationDate;
    private int userId;
    //private String userName;

    public QuizViewModel() {
    }

    public QuizViewModel(int id, int categoryId, String name, int questionNumber, Integer time, String isConfirmed, String creationDate, int userId) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.questionNumber = questionNumber;
        this.time = time;
        this.isConfirmed = isConfirmed;
        this.creationDate = creationDate;
        this.userId = userId;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(String isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

