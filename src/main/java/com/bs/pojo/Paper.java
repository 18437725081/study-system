package com.bs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 试卷
 *
 * @author 暗香
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paper {

    /**
     * 试卷表主键
     */
    private Integer pkPaper;

    /**
     * 试卷名称
     */
    private String paperName;

    /**
     * 公开状态
     */
    private String flagPublic;

    /**
     * 编辑状态
     */
    private String flagEdit;

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
     * 最后一次更新人
     */
    private Integer lastUpdatedBy;

    /**
     * 最后一次更新时间
     */
    private Date lastUpdatedTime;
}