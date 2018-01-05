package com.bs.pojo;

import java.util.Date;

public class Student {
    private Integer pkStudent;

    private String username;

    private String password;

    private String question;

    private String answer;

    private String name;

    private String studentId;

    private Integer fkMajor;

    private String role;

    private Integer createdBy;

    private Date createdTime;

    private Integer lastUpdatedBy;

    private Date lastUpdatedTime;

    public Student(Integer pkStudent, String username, String password, String question, String answer, String name, String studentId, Integer fkMajor, String role, Integer createdBy, Date createdTime, Integer lastUpdatedBy, Date lastUpdatedTime) {
        this.pkStudent = pkStudent;
        this.username = username;
        this.password = password;
        this.question = question;
        this.answer = answer;
        this.name = name;
        this.studentId = studentId;
        this.fkMajor = fkMajor;
        this.role = role;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Student() {
        super();
    }

    public Integer getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(Integer pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public Integer getFkMajor() {
        return fkMajor;
    }

    public void setFkMajor(Integer fkMajor) {
        this.fkMajor = fkMajor;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}