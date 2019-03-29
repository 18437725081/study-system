package com.bs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 教师
 *
 * @author 暗香
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    /**
     * 教师表主键
     */
    private Integer pkTeacher;

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
     * 教师姓名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

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
     * 最后修改人
     */
    private Integer lastUpdatedBy;

    /**
     * 最后修改时间
     */
    private Date lastUpdatedTime;
}