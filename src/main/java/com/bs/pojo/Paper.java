package com.bs.pojo;

import java.util.Date;

public class Paper {
    private Integer pkPaper;

    private String paperName;

    private String flagPublic;

    private String flagEdit;

    private String flag;

    private Integer createdBy;

    private Date createdTime;

    private Integer lastUpdatedBy;

    private Date lastUpdatedTime;

    public Paper(Integer pkPaper, String paperName, String flagPublic, String flagEdit, String flag, Integer createdBy, Date createdTime, Integer lastUpdatedBy, Date lastUpdatedTime) {
        this.pkPaper = pkPaper;
        this.paperName = paperName;
        this.flagPublic = flagPublic;
        this.flagEdit = flagEdit;
        this.flag = flag;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Paper() {
        super();
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
        this.paperName = paperName == null ? null : paperName.trim();
    }

    public String getFlagPublic() {
        return flagPublic;
    }

    public void setFlagPublic(String flagPublic) {
        this.flagPublic = flagPublic == null ? null : flagPublic.trim();
    }

    public String getFlagEdit() {
        return flagEdit;
    }

    public void setFlagEdit(String flagEdit) {
        this.flagEdit = flagEdit == null ? null : flagEdit.trim();
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