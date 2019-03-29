package com.bs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 教师专业关联
 *
 * @author 暗香
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelTeacherMajor {

    /**
     * 关联表主键
     */
    private Integer pkRelTeacherMajor;

    /**
     * 教师表外键
     */
    private Integer fkTeacher;

    /**
     * 专业表外键
     */
    private Integer fkMajor;

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