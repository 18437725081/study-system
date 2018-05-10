package com.bs.vo;

/**
 * @author 张靖烽
 * @name ScoreDisplayVO
 * @description
 * @create 2018-04-03 22:22
 **/
public class ScoreDisplayVO {

    private String studentName;

    private String studentId;

    private String major;

    private String paperName;

    private String score;

    @Override
    public String toString() {
        return "ScoreDisplayVO{" +
                "studentName='" + studentName + '\'' +
                ", studentId='" + studentId + '\'' +
                ", major='" + major + '\'' +
                ", paperName='" + paperName + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
