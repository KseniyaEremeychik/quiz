package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private double percent;
    private int quizNum;

    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "percent")
    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    @Basic
    @Column(name = "quiz_num")
    public int getQuizNum() {
        return quizNum;
    }

    public void setQuizNum(int quizNum) {
        this.quizNum = quizNum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return getId() == rating.getId() &&
                getUserId() == rating.getUserId() &&
                Double.compare(rating.getPercent(), getPercent()) == 0 &&
                getQuizNum() == rating.getQuizNum();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getPercent(), getQuizNum());
    }
}
