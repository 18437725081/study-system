package com.bs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 暗香
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentVO {
    /**
     * 学生表主键
     */
    private Integer pkStudent;
    /**
     * 用户名
     */
    private String username;
    /**
     * 姓名
     */
    private String name;
    /**
     * 学号
     */
    private String studentId;
    /**
     * 专业主键
     */
    private String pkMajor;
    /**
     * 专业
     */
    private String major;
    /**
     * 年级
     */
    private String grade;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date lastUpdatedTime;
}
