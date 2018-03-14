package com.bs.vo;

import java.util.List;

/**
 * @author 张靖烽
 * @name PaperDetailVO
 * @description
 * @create 2018-03-14 20:07
 **/
public class PaperDetailVO {

    private String paperName;

    private String createdBy;

    private String score;

    private List<ChoiceQuestionVO> choiceQuestion;

    @Override
    public String toString() {
        return "PaperDetailVO{" +
                "paperName='" + paperName + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", score='" + score + '\'' +
                ", choiceQuestion=" + choiceQuestion +
                '}';
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
