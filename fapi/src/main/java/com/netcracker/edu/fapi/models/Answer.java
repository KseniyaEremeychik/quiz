package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Answer {
    private int id;
    private int questionId;
    private int ordering;
    private String text;
    private byte isRight;

    public Answer() {
    }

    public Answer(int id, int questionId, int ordering, String text, byte isRight) {
        this.id = id;
        this.questionId = questionId;
        this.ordering = ordering;
        this.text = text;
        this.isRight = isRight;
    }

    /*public Answer(int id, int questionId, int order, String text) {
        this.id = id;
        this.questionId = questionId;
        this.order = order;
        this.text = text;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte getIsRight() {
        return isRight;
    }

    public void setIsRight(byte isRight) {
        this.isRight = isRight;
    }


}
