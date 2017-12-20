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
         * 普通用户
         */
        int ROLE_CUSTOMER = 0;
        /**
         * 管理员用户
         */
        int ROLE_ADMIN = 1;
    }
}
