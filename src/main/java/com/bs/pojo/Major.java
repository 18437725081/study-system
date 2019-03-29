package com.bs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 专业
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Major {
    /**
     * 专业表主键
     */
    private Integer pkMajor;

    /**
     * 专业名称
     */
    private String major;

    /**
     * 年级
     */
    private String grade;

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