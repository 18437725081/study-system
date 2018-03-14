package com.bs.pojo;

import java.util.Date;

/**
 * @author 张靖烽
 * @description 试卷pojo
 * @createtime 2018-03-14 14:29
 */
public class Paper {
    /** 试卷表主键 */
    private Integer pkPaper;
    /** 试卷名称 */
    private String paperName;
    /** 公开状态 */
    private String flagPublic;
    /** 编辑状态 */
    private String flagEdit;
    /** 是否有效 */
    private String flag;
    /** 创建人 */
    private Integer createdBy;
    /** 创建时间 */
    private Date createdTime;
    /** 最后一次更新人 */
    private Integer lastUpdatedBy;
    /** 最后一次更新时间 */
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