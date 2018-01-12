package com.bs.dao;

import com.bs.pojo.Manager;
import org.apache.ibatis.annotations.Param;

/**
 * @author 张靖烽
 * @description 管理员dao层
 * @createtime 2017-12-26 13:27
 */
public interface ManagerMapper {
    /**
     * 根据主键删除
     *
     * @param pkManager 管理员主键
     * @return int
     * @author 张靖烽
     * @createtime 2017-12-26 13:28
     */
    int deleteByPrimaryKey(Integer pkManager);

    /**
     * 新增管理员
     *
     * @param manager 管理员类
     * @return int
     * @author 张靖烽
     * @createtime 2017-12-26 13:31
     */
    int insert(Manager manager);

    /**
     * 新增管理员
     *
     * @param manager 管理员类
     * @return int
     * @author 张靖烽
     * @createtime 2017-12-26 13:33
     */
    int insertSelective(Manager manager);

    /**
     * 通过主键查询
     *
     * @param pkManager 管理员主键
     * @return Manager类对象
     * @author 张靖烽
     * @createtime 2017-12-26 13:33
     */
    Manager selectByPrimaryKey(Integer pkManager);

    /**
     * 更新
     *
     * @param manager 管理员类
     * @return int
     * @author 张靖烽
     * @createtime 2017-12-26 13:34
     */
    int updateByPrimaryKeySelective(Manager manager);

    /**
     * 更新
     *
     * @param manager 管理员类
     * @return int
     * @author 张靖烽
     * @createtime 2017-12-26 13:34
     */
    int updateByPrimaryKey(Manager manager);

    /**
     * 检查用户名是否存在
     *
     * @param username 用户名
     * @return int
     * @author 张靖烽
     * @createtime 2017-12-26 13:34
     */
    int checkUsername(String username);

    /**
     * 登录，检查用户名密码是否正确
     *
     * @param username    用户名
     * @param password 密码
     * @return Manager
     * @author 张靖烽
     * @createtime 2017-12-26 13:35
     */
    Manager login(@Param("username") String username, @Param("password") String password);
}