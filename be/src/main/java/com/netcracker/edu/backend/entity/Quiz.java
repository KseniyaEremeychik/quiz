package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;


@Entity
public class Quiz {
    private int id;
    private int categoryId;
    private String name;
    private int questionNumber;
    private Integer time;

    @Enumerated(EnumType.STRING)
    private Confirmation isConfirmed;
    private Date creationDate;
    private int userId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "question_number")
    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    @Basic
    @Column(name = "time")
    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Basic
    @Column(name = "is_confirmed")
    @Enumerated(EnumType.STRING)
    public Confirmation getIsConfirmed() {
        return isConfirmed;
    }

    @Enumerated(EnumType.STRING)
    public void setIsConfirmed(Confirmation isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    @Basic
    @Column(name = "creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return id == quiz.id &&
                categoryId == quiz.categoryId &&
                questionNumber == quiz.questionNumber &&
                userId == quiz.userId &&
                Objects.equals(name, quiz.name) &&
                Objects.equals(time, quiz.time) &&
                Objects.equals(isConfirmed, quiz.isConfirmed) &&
                Objects.equals(creationDate, quiz.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId, name, questionNumber, time, isConfirmed, creationDate, userId);
    }

    public enum Confirmation {
        approved,
        rejected,
        unseen
    }
}
