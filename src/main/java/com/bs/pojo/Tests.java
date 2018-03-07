package com.bs.pojo;

import java.util.Date;
/**
 * @author 张靖烽
 * @description 试题表pojo类
 * @createtime 2018-03-07 19:15
 */
public class Tests {
    /** 试题表主键 */
    private Integer pkTest;
    /** 试题类型 */
    private String testType;
    /** 试题所属学科 */
    private String testSubject;
    /** 试题题目 */
    private String testTitle;
    /** 试题内容 */
    private String testContent;
    /** 试题答案 */
    private String testAnswer;
    /** 试题解析 */
    private String testAnalyze;
    /** 是否有效 */
    private String flag;
    /** 创建人 */
    private Integer createdBy;
    /** 创建时间 */
    private Date createdTime;
    /** 最后一次更新人 */
    private Integer lastUpdatedBy;
    /** 最后一次更新时间 */
    private Date lastUpdatedTime;

    public Tests(Integer pkTest, String testType, String testSubject, String testTitle, String testContent, String testAnswer, String testAnalyze, String flag, Integer createdBy, Date createdTime, Integer lastUpdatedBy, Date lastUpdatedTime) {
        this.pkTest = pkTest;
        this.testType = testType;
        this.testSubject = testSubject;
        this.testTitle = testTitle;
        this.testContent = testContent;
        this.testAnswer = testAnswer;
        this.testAnalyze = testAnalyze;
        this.flag = flag;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Tests() {
        super();
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
        this.testType = testType == null ? null : testType.trim();
    }

    public String getTestSubject() {
        return testSubject;
    }

    public void setTestSubject(String testSubject) {
        this.testSubject = testSubject == null ? null : testSubject.trim();
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle == null ? null : testTitle.trim();
    }

    public String getTestContent() {
        return testContent;
    }

    public void setTestContent(String testContent) {
        this.testContent = testContent == null ? null : testContent.trim();
    }

    public String getTestAnswer() {
        return testAnswer;
    }

    public void setTestAnswer(String testAnswer) {
        this.testAnswer = testAnswer == null ? null : testAnswer.trim();
    }

    public String getTestAnalyze() {
        return testAnalyze;
    }

    public void setTestAnalyze(String testAnalyze) {
        this.testAnalyze = testAnalyze == null ? null : testAnalyze.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
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