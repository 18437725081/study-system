package com.bs.vo;

/**
 * @author 张靖烽
 * @name PaperTestsVO
 * @description
 * @create 2018-03-28 9:00
 **/
public class PaperTestsVO {
    /** 试题主键 */
    private Integer pkTest;
    /** 题目 */
    private String testTitle;
    /** 分值 */
    private String score;
    /** 优先级 */
    private String priority;

    @Override
    public String toString() {
        return "PaperTestsVO{" +
                "pkTest=" + pkTest +
                ", testTitle='" + testTitle + '\'' +
                ", score='" + score + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }

    public Integer getPkTest() {
        return pkTest;
    }

    public void setPkTest(Integer pkTest) {
        this.pkTest = pkTest;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
