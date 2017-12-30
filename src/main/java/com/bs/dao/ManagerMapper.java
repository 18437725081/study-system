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
     * @author 张靖烽
     * @param pkManager 管理员主键
     * @return int
     * @createtime 2017-12-26 13:28
     */
    int deleteByPrimaryKey(Integer pkManager);

    /**
     * 新增管理员
     *
     * @author 张靖烽
     * @param record 管理员类
     * @return int
     * @createtime 2017-12-26 13:31
     */
    int insert(Manager record);

    /**
     * 新增管理员
     *
     * @author 张靖烽
     * @param record 管理员类
     * @return int
     * @createtime 2017-12-26 13:33
     */
    int insertSelective(Manager record);

    /**
     * 通过主键查询
     *
     * @author 张靖烽
     * @param pkManager 管理员主键
     * @return Manager类对象
     * @createtime 2017-12-26 13:33
     */
    Manager selectByPrimaryKey(Integer pkManager);

    /**
     * 更新
     *
     * @author 张靖烽
     * @param record 管理员类
     * @return int
     * @createtime 2017-12-26 13:34
     */
    int updateByPrimaryKeySelective(Manager record);

    /**
     * 更新
     *
     * @author 张靖烽
     * @param record 管理员类
     * @return int
     * @createtime 2017-12-26 13:34
     */
    int updateByPrimaryKey(Manager record);

    /**
     * 检查用户名是否存在
     *
     * @author 张靖烽
     * @param username 用户名
     * @return int
     * @createtime 2017-12-26 13:34
     */
    int checkUsername(String username);

    /**
     * 登录，检查用户名密码是否正确
     *
     * @author 张靖烽
     * @param username 用户名
     * @param md5Password 密码
     * @return Manager
     * @createtime 2017-12-26 13:35
     */
    Manager login(@Param("username") String username,@Param("md5Password") String md5Password);
}