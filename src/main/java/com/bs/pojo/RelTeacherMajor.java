package com.bs.pojo;

import java.util.Date;

public class RelTeacherMajor {
    private Integer pkRelTeacherMajor;

    private Integer fkTeacher;

    private Integer fkMajor;

    private Integer createdBy;

    private Date createdTime;

    private Integer lastUpdatedBy;

    private Date lastUpdatedTime;

    public RelTeacherMajor(Integer pkRelTeacherMajor, Integer fkTeacher, Integer fkMajor, Integer createdBy, Date createdTime, Integer lastUpdatedBy, Date lastUpdatedTime) {
        this.pkRelTeacherMajor = pkRelTeacherMajor;
        this.fkTeacher = fkTeacher;
        this.fkMajor = fkMajor;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public RelTeacherMajor() {
        super();
    }

    public Integer getPkRelTeacherMajor() {
        return pkRelTeacherMajor;
    }

    public void setPkRelTeacherMajor(Integer pkRelTeacherMajor) {
        this.pkRelTeacherMajor = pkRelTeacherMajor;
    }

    public Integer getFkTeacher() {
        return fkTeacher;
    }

    public void setFkTeacher(Integer fkTeacher) {
        this.fkTeacher = fkTeacher;
    }

    public Integer getFkMajor() {
        return fkMajor;
    }

    public void setFkMajor(Integer fkMajor) {
        this.fkMajor = fkMajor;
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