package com.bs.vo;

/**
 * @author 张靖烽
 * @name choiceQuestionVO
 * @description
 * @create 2018-03-14 20:09
 **/
public class ChoiceQuestionVO {

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
     * 选项A
     */
    private String optionA;
    /**
     * 选项B
     */
    private String optionB;
    /**
     * 选项C
     */
    private String optionC;
    /**
     * 选项D
     */
    private String optionD;
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
     * 分值
     */
    private String score;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 最后一次更新时间
     */
    private String lastUpdatedTime;

    @Override
    public String toString() {
        return "ChoiceQuestionVO{" +
                "pkTest=" + pkTest +
                ", testType='" + testType + '\'' +
                ", testSubject='" + testSubject + '\'' +
                ", testTitle='" + testTitle + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", testAnswer='" + testAnswer + '\'' +
                ", testAnalyze='" + testAnalyze + '\'' +
                ", flag='" + flag + '\'' +
                ", score='" + score + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdatedTime='" + lastUpdatedTime + '\'' +
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

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(String lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
