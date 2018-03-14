package com.bs.pojo;

/**
 * @author 张靖烽
 * @description 试卷详情pojo
 * @createtime 2018-03-14 14:29
 */
public class PaperDetail {
    /** 试卷详情表主键 */
    private Integer pkPaperDetail;
    /** 试卷表外键 */
    private Integer fkPaper;
    /** 题目表外键 */
    private Integer fkTests;
    /** 题目类型 */
    private String testsType;
    /** 分值 */
    private String score;
    /** 优先级 */
    private String priority;

    public PaperDetail(Integer pkPaperDetail, Integer fkPaper, Integer fkTests, String testsType, String score, String priority) {
        this.pkPaperDetail = pkPaperDetail;
        this.fkPaper = fkPaper;
        this.fkTests = fkTests;
        this.testsType = testsType;
        this.score = score;
        this.priority = priority;
    }

    public PaperDetail() {
        super();
    }

    public Integer getPkPaperDetail() {
        return pkPaperDetail;
    }

    public void setPkPaperDetail(Integer pkPaperDetail) {
        this.pkPaperDetail = pkPaperDetail;
    }

    public Integer getFkPaper() {
        return fkPaper;
    }

    public void setFkPaper(Integer fkPaper) {
        this.fkPaper = fkPaper;
    }

    public Integer getFkTests() {
        return fkTests;
    }

    public void setFkTests(Integer fkTests) {
        this.fkTests = fkTests;
    }

    public String getTestsType() {
        return testsType;
    }

    public void setTestsType(String testsType) {
        this.testsType = testsType == null ? null : testsType.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority == null ? null : priority.trim();
    }
}