package com.bs.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 暗香
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeVO {
    /**
     * 通知主键
     */
    private Integer pkNotice;
    /**
     * 通知内容
     */
    private String noticeContent;
    /**
     * 是否有效
     */
    private String flag;
    /**
     * 创建人
     */
    private Integer createdBy;
    /**
     * 创建时间
     */
    private String createdTime;
    /**
     * 最后修改人
     */
    private Integer lastUpdatedBy;
    /**
     * 最后修改时间
     */
    private String lastUpdatedTime;
}