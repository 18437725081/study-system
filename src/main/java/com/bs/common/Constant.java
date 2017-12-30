package com.bs.common;


/**
 * @author 张靖烽
 * @name Constant
 * @description 常量类，存放一些常亮信息
 * @create 2017-10-16 9:27
 **/
public class Constant {
    /**
     * 当前用户
     */
    public static final String CURRENT_USER = "currentUser";

    public interface Role {
        /**
         * 管理员
         */
        String ROLE_ADMIN = "0";
        /**
         * 教师
         */
        String ROLE_TEACHER = "1";
        /**
         * 学生
         */
        String ROLE_STUDENT = "2";
    }
}