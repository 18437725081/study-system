package com.bs.pojo;

import java.util.Date;
/**
 * @author 张靖烽
 * @description 创建pojo
 * @createtime 2018-03-14 14:30
 */
public class Score {
    /** 成绩表主键 */
    private Integer pkScore;
    /** 学生表外键 */
    private Integer fkStudent;
    /** 试卷表外键 */
    private Integer fkPaper;
    /** 成绩 */
    private String score;
    /** 完成状态 */
    private String flag;
    /** 完成时间 */
    private Date finishTime;

    public Score(Integer pkScore, Integer fkStudent, Integer fkPaper, String score, String flag, Date finishTime) {
        this.pkScore = pkScore;
        this.fkStudent = fkStudent;
        this.fkPaper = fkPaper;
        this.score = score;
        this.flag = flag;
        this.finishTime = finishTime;
    }

    public Score() {
        super();
    }

    public Integer getPkScore() {
        return pkScore;
    }

    public void setPkScore(Integer pkScore) {
        this.pkScore = pkScore;
    }

    public Integer getFkStudent() {
        return fkStudent;
    }

    public void setFkStudent(Integer fkStudent) {
        this.fkStudent = fkStudent;
    }

    public Integer getFkPaper() {
        return fkPaper;
    }

    public void setFkPaper(Integer fkPaper) {
        this.fkPaper = fkPaper;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}