package com.bs.pojo;

import java.util.Date;

/**
 * @author 张靖烽
 * @description 关联pojo（教师&专业）
 * @createtime 2018-01-10 14:22
 */
public class RelTeacherMajor {
    /** 关联表主键 */
    private Integer pkRelTeacherMajor;
    /** 教师表外键 */
    private Integer fkTeacher;
    /** 专业表外键 */
    private Integer fkMajor;
    /** 创建人 */
    private Integer createdBy;
    /** 创建时间 */
    private Date createdTime;
    /** 最后一次更新人 */
    private Integer lastUpdatedBy;
    /** 最后一次更新时间 */
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