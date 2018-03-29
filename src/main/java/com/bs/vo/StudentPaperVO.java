package com.bs.vo;

/**
 * @author 张靖烽
 * @name studentPaperVO
 * @description
 * @create 2018-03-29 16:10
 **/
public class StudentPaperVO {
    /**
     * 试卷主键
     */
    private Integer pkPaper;
    /**
     * 试卷名称
     */
    private String paperName;
    /**
     * 发布时间
     */
    private String publicTime;

    @Override
    public String toString() {
        return "studentPaperVO{" +
                "pkPaper=" + pkPaper +
                ", paperName='" + paperName + '\'' +
                ", publicTime='" + publicTime + '\'' +
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

    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }
}
