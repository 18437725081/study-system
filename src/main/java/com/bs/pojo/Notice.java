package com.bs.pojo;

import java.util.Date;
/**
 * @author 张靖烽
 * @description 通知pojo类
 * @createtime 2017-12-21 9:51
 */
public class Notice {
    /** 通知主键 */
    private Integer pkNotice;
    /** 通知内容 */
    private String noticeContent;
    /** 是否有效 */
    private String flag;
    /** 创建人 */
    private Integer createdBy;
    /** 创建时间 */
    private Date createdTime;
    /** 最后修改人 */
    private Integer lastUpdatedBy;
    /** 最后修改时间 */
    private Date lastUpdatedTime;

    public Notice(Integer pkNotice, String noticeContent, String flag, Integer createdBy, Date createdTime, Integer lastUpdatedBy, Date lastUpdatedTime) {
        this.pkNotice = pkNotice;
        this.noticeContent = noticeContent;
        this.flag = flag;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Notice() {
        super();
    }

    public Integer getPkNotice() {
        return pkNotice;
    }

    public void setPkNotice(Integer pkNotice) {
        this.pkNotice = pkNotice;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent == null ? null : noticeContent.trim();
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