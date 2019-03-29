package com.bs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 通知类
 * @author 暗香
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {

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
    private Date createdTime;

    /**
     * 最后修改人
     */
    private Integer lastUpdatedBy;

    /**
     * 最后修改时间
     */
    private Date lastUpdatedTime;
}