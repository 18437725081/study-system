package com.bs.pojo;

/**
 * @author 张靖烽
 * @description 管理员pojo类
 * @createtime 2017-12-26 13:26
 */
public class Manager {
    /** 管理员主键 */
    private Integer pkManager;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 角色 */
    private String role;

    public Manager(Integer pkManager, String username, String password, String role) {
        this.pkManager = pkManager;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Manager() {
        super();
    }

    public Integer getPkManager() {
        return pkManager;
    }

    public void setPkManager(Integer pkManager) {
        this.pkManager = pkManager;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }
}