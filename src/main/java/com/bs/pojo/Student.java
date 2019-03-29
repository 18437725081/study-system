package com.bs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 学生表
 *
 * @author 暗香
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    /**
     * 学生表主键
     */
    private Integer pkStudent;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 找回密码问题
     */
    private String question;
    /**
     * 找回密码答案
     */
    private String answer;

    /**
     * 姓名
     */
    private String name;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 专业表外键
     */
    private Integer fkMajor;

    /**
     * 角色
     */
    private String role;

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