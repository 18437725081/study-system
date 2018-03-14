package com.bs.pojo;

import java.util.Date;

public class Score {
    private Integer pkScore;

    private Integer fkStudent;

    private Integer fkTests;

    private String score;

    private String flag;

    private Date finishTime;

    public Score(Integer pkScore, Integer fkStudent, Integer fkTests, String score, String flag, Date finishTime) {
        this.pkScore = pkScore;
        this.fkStudent = fkStudent;
        this.fkTests = fkTests;
        this.score = score;
        this.flag = flag;
        this.finishTime = finishTime;
    }

    public Score() {
        super();
    }

    public Integer getPkScore() {
        return pkScore;
    }

    public void setPkScore(Integer pkScore) {
        this.pkScore = pkScore;
    }

    public Integer getFkStudent() {
        return fkStudent;
    }

    public void setFkStudent(Integer fkStudent) {
        this.fkStudent = fkStudent;
    }

    public Integer getFkTests() {
        return fkTests;
    }

    public void setFkTests(Integer fkTests) {
        this.fkTests = fkTests;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}