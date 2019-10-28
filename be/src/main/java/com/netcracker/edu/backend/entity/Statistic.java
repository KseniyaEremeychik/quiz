package com.netcracker.edu.backend.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
public class Statistic {
    private int id;
    private int quizId;
    private double rightAnswersPercent;
    private Time timeSpent;
    private Date passageDate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "quiz_id")
    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    @Basic
    @Column(name = "right_answers_percent")
    public double getRightAnswersPercent() {
        return rightAnswersPercent;
    }

    public void setRightAnswersPercent(double rightAnswersPercent) {
        this.rightAnswersPercent = rightAnswersPercent;
    }

    @Basic
    @Column(name = "time_spent")
    public Time getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Time timeSpent) {
        this.timeSpent = timeSpent;
    }

    @Basic
    @Column(name = "passage_date")
    public Date getPassageDate() {
        return passageDate;
    }

    public void setPassageDate(Date passageDate) {
        this.passageDate = passageDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return id == statistic.id &&
                quizId == statistic.quizId &&
                Double.compare(statistic.rightAnswersPercent, rightAnswersPercent) == 0 &&
                Objects.equals(timeSpent, statistic.timeSpent) &&
                Objects.equals(passageDate, statistic.passageDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quizId, rightAnswersPercent, timeSpent, passageDate);
    }
}
