package com.bs.pojo;

import java.util.Date;

public class Major {
    private Integer pkMajor;

    private String major;

    private Integer grade;

    private Integer createdBy;

    private Date createdTime;

    private Integer lastUpdatedBy;

    private Date lastUpdatedTime;

    public Major(Integer pkMajor, String major, Integer grade, Integer createdBy, Date createdTime, Integer lastUpdatedBy, Date lastUpdatedTime) {
        this.pkMajor = pkMajor;
        this.major = major;
        this.grade = grade;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Major() {
        super();
    }

    public Integer getPkMajor() {
        return pkMajor;
    }

    public void setPkMajor(Integer pkMajor) {
        this.pkMajor = pkMajor;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
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