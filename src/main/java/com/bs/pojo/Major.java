package com.bs.pojo;

import java.util.Date;

/**
 * @author 张靖烽
 * @description 专业表pojo类
 * @createtime 2018-01-10 14:23
 */
public class Major {
    /** 专业表主键 */
    private Integer pkMajor;
    /** 专业名称 */
    private String major;
    /** 年级 */
    private Integer grade;
    /** 创建人 */
    private Integer createdBy;
    /** 创建时间 */
    private Date createdTime;
    /** 最后一次更新人 */
    private Integer lastUpdatedBy;
    /** 最后一次更新时间 */
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