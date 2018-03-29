package com.bs.vo;

import java.util.List;

/**
 * @author 张靖烽
 * @name PaperDetailVO
 * @description
 * @create 2018-03-14 20:07
 **/
public class PaperDetailVO {
    /**
     * 试卷主键
     */
    private Integer pkPaper;
    /**
     * 试卷名称
     */
    private String paperName;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 总分
     */
    private String score;
    /**
     * 选择题VO
     */
    private List<ChoiceQuestionVO> choiceQuestion;

    @Override
    public String toString() {
        return "PaperDetailVO{" +
                "pkPaper='" + pkPaper + '\'' +
                ", paperName='" + paperName + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", score='" + score + '\'' +
                ", choiceQuestion=" + choiceQuestion +
                '}';
    }

    public Integer getPkPaper() {
        return pkPaper;
    }

    public void setPkPaper(Integer pkPaper) {
        this.pkPaper = pkPaper;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public List<ChoiceQuestionVO> getChoiceQuestion() {
        return choiceQuestion;
    }

    public void setChoiceQuestion(List<ChoiceQuestionVO> choiceQuestion) {
        this.choiceQuestion = choiceQuestion;
    }
}
