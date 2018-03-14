package com.bs.pojo;

import java.util.Date;

/**
 * @author 张靖烽
 * @description 关联pojo（试卷&专业）
 * @createtime 2018-03-14 14:30
 */
public class RelPaperMajor {
    /** 关联（试卷&专业）表主键 */
    private Integer pkRelPaperMajor;
    /** 试卷表外键 */
    private Integer fkPaper;
    /** 专业表外键 */
    private Integer fkMajor;
    /** 发布时间 */
    private Date publishTime;

    public RelPaperMajor(Integer pkRelPaperMajor, Integer fkPaper, Integer fkMajor, Date publishTime) {
        this.pkRelPaperMajor = pkRelPaperMajor;
        this.fkPaper = fkPaper;
        this.fkMajor = fkMajor;
        this.publishTime = publishTime;
    }

    public RelPaperMajor() {
        super();
    }

    public Integer getPkRelPaperMajor() {
        return pkRelPaperMajor;
    }

    public void setPkRelPaperMajor(Integer pkRelPaperMajor) {
        this.pkRelPaperMajor = pkRelPaperMajor;
    }

    public Integer getFkPaper() {
        return fkPaper;
    }

    public void setFkPaper(Integer fkPaper) {
        this.fkPaper = fkPaper;
    }

    public Integer getFkMajor() {
        return fkMajor;
    }

    public void setFkMajor(Integer fkMajor) {
        this.fkMajor = fkMajor;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}