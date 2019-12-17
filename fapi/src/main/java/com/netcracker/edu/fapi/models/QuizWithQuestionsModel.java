package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuizWithQuestionsModel {
    private int id;
    private int categoryId;

    @NotEmpty()
    @Size(max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9!?,._\\s-]+$")
    private String name;
    private int questionNumber;
    private String isConfirmed;
    private String creationDate;
    private int userId;

    @NotEmpty
    private List<QuestionViewModel> questions;

    public QuizWithQuestionsModel() {
    }

    public QuizWithQuestionsModel(int id, int categoryId, String name, int questionNumber, String isConfirmed, String creationDate, int userId, List<QuestionViewModel> questions) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.questionNumber = questionNumber;
        this.isConfirmed = isConfirmed;
        this.creationDate = creationDate;
        this.userId = userId;
        this.questions = questions;
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

    public List<QuestionViewModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionViewModel> questions) {
        this.questions = questions;
    }
}
