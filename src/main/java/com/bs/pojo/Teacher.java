package com.bs.pojo;

import java.util.Date;
/**
 * @author 张靖烽
 * @description 教师表pojo类
 * @createtime 2018-01-03 20:46
 */
public class Teacher {
    /** 教师表主键 */
    private Integer pkTeacher;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 找回密码问题 */
    private String question;
    /** 找回密码答案 */
    private String answer;
    /** 教师姓名 */
    private String name;
    /** 电话 */
    private String phone;
    /** 角色 */
    private String role;
    /** 创建人 */
    private Integer createdBy;
    /** 创建时间 */
    private Date createdTime;
    /** 最后修改人 */
    private Integer lastUpdatedBy;
    /** 最后修改时间 */
    private Date lastUpdatedTime;

    public Teacher(Integer pkTeacher, String username, String password, String question, String answer, String name, String phone, String role, Integer createdBy, Date createdTime, Integer lastUpdatedBy, Date lastUpdatedTime) {
        this.pkTeacher = pkTeacher;
        this.username = username;
        this.password = password;
        this.question = question;
        this.answer = answer;
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Teacher() {
        super();
    }

    public Integer getPkTeacher() {
        return pkTeacher;
    }

    public void setPkTeacher(Integer pkTeacher) {
        this.pkTeacher = pkTeacher;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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