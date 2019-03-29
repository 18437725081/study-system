package com.bs.dao;

import com.bs.pojo.Manager;
import org.apache.ibatis.annotations.Param;

/**
 * 管理员
 *
 * @author 暗香
 */
public interface ManagerMapper {

    /**
     * 根据主键删除
     *
     * @param pkManager 管理员主键
     * @return int
     */
    int deleteByPrimaryKey(Integer pkManager);

    /**
     * 新增管理员
     *
     * @param manager 管理员类
     * @return int
     */
    int insert(Manager manager);

    /**
     * 新增管理员
     *
     * @param manager 管理员类
     * @return int
     */
    int insertSelective(Manager manager);

    /**
     * 通过主键查询
     *
     * @param pkManager 管理员主键
     * @return Manager类对象
     */
    Manager selectByPrimaryKey(Integer pkManager);

    /**
     * 更新
     *
     * @param manager
     * @return
     */
    int updateByPrimaryKeySelective(Manager manager);

    /**
     * 更新
     *
     * @param manager
     * @return
     */
    int updateByPrimaryKey(Manager manager);

    /**
     * 检查用户名是否存在
     *
     * @param username
     * @return
     */
    int checkUsername(String username);

    /**
     * 登录，检查用户名密码是否正确
     *
     * @param username 用户名
     * @param password 密码
     * @return Manager
     */
    Manager login(@Param("username") String username, @Param("password") String password);
}