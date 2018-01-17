package com.bs.vo;

import java.util.Date;

/**
 * @author 张靖烽
 * @name StudentVO
 * @description
 * @create 2018-01-17 10:25
 **/
public class StudentVO {
    /** 学生表主键 */
    private Integer pkStudent;
    /** 用户名 */
    private String username;
    /** 姓名 */
    private String name;
    /** 学号 */
    private String studentId;
    /** 专业 */
    private String major;
    /** 年级 */
    private Integer grade;
    /** 创建时间 */
    private Date createdTime;
    /** 更新时间 */
    private Date lastUpdatedTime;

    @Override
    public String toString() {
        return "StudentVO{" +
                "pkStudent=" + pkStudent +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", studentId='" + studentId + '\'' +
                ", major='" + major + '\'' +
                ", grade='" + grade + '\'' +
                ", createdTime=" + createdTime +
                ", lastUpdatedTime=" + lastUpdatedTime +
                '}';
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
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
