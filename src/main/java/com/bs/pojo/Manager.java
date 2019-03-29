package com.bs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员
 *
 * @author 暗香
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manager {

    /**
     * 管理员主键
     */
    private Integer pkManager;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private String role;
}