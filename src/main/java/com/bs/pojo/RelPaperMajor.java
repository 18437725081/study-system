package com.bs.pojo;

import java.util.Date;

public class RelPaperMajor {
    private Integer pkRelPaperMajor;

    private Integer fkPaper;

    private Integer fkMajor;

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