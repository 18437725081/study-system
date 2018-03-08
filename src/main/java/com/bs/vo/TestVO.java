package com.bs.vo;

import java.util.Date;

/**
 * @author 张靖烽
 * @name TestVO
 * @description
 * @create 2018-03-08 14:42
 **/
public class TestVO {
    /**
     * 试题表主键
     */
    private Integer pkTest;
    /**
     * 试题类型
     */
    private String testType;
    /**
     * 试题所属学科
     */
    private String testSubject;
    /**
     * 试题题目
     */
    private String testTitle;
    /**
     * 试题内容
     */
    private String testContent;
    /**
     * 试题答案
     */
    private String testAnswer;
    /**
     * 试题解析
     */
    private String testAnalyze;
    /**
     * 是否有效
     */
    private String flag;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 最后一次更新时间
     */
    private Date lastUpdatedTime;

    @Override
    public String toString() {
        return "TestVO{" +
                "pkTest=" + pkTest +
                ", testType='" + testType + '\'' +
                ", testSubject='" + testSubject + '\'' +
                ", testTitle='" + testTitle + '\'' +
                ", testContent='" + testContent + '\'' +
                ", testAnswer='" + testAnswer + '\'' +
                ", testAnalyze='" + testAnalyze + '\'' +
                ", flag='" + flag + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdatedTime=" + lastUpdatedTime +
                '}';
    }

    public Integer getPkTest() {
        return pkTest;
    }

    public void setPkTest(Integer pkTest) {
        this.pkTest = pkTest;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getTestSubject() {
        return testSubject;
    }

    public void setTestSubject(String testSubject) {
        this.testSubject = testSubject;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public String getTestContent() {
        return testContent;
    }

    public void setTestContent(String testContent) {
        this.testContent = testContent;
    }

    public String getTestAnswer() {
        return testAnswer;
    }

    public void setTestAnswer(String testAnswer) {
        this.testAnswer = testAnswer;
    }

    public String getTestAnalyze() {
        return testAnalyze;
    }

    public void setTestAnalyze(String testAnalyze) {
        this.testAnalyze = testAnalyze;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
