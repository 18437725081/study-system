package com.bs.dao;

import com.bs.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 张靖烽
 * @description 教师DAO层
 * @createtime 2018-01-03 20:48
 */
public interface TeacherMapper {
    /**
     * 根据主键删除教师
     *
     * @param pkTeacher 教师主键
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-03 20:49
     */
    int deleteByPrimaryKey(Integer pkTeacher);

    /**
     * 新增教师
     *
     * @param teacher 教师类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-03 20:49
     */
    int insert(Teacher teacher);

    /**
     * 新增教师
     *
     * @param teacher 教师类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-03 20:49
     */
    int insertSelective(Teacher teacher);

    /**
     * 通过主键查询教师
     *
     * @param pkTeacher 教师主键
     * @return Teacher
     * @author 张靖烽
     * @createtime 2018-01-03 20:49
     */
    Teacher selectByPrimaryKey(Integer pkTeacher);

    /**
     * 更新教师
     *
     * @param teacher 教师类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-03 20:49
     */
    int updateByPrimaryKeySelective(Teacher teacher);

    /**
     * 更新教师
     *
     * @param teacher 教师类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-03 20:49
     */
    int updateByPrimaryKey(Teacher teacher);

    /**
     * 查询所有教师信息
     *
     * @return List<Teacher>
     * @author 张靖烽
     * @createtime 2017-12-29 11:12
     */
    List<Teacher> selectAllTeacher();

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
     * @param username 用户名
     * @param password 密码
     * @return Manager
     * @author 张靖烽
     * @createtime 2017-12-26 13:35
     */
    Teacher login(@Param("username") String username, @Param("password") String password);

    /**
     * 获取问题
     *
     * @param username 用户名
     * @return Manager
     * @author 张靖烽
     * @createtime 2018-01-12 14:37
     */
    String selectQuestionByTeacher(String username);

    /**
     * 校验答案
     *
     * @param username 用户名
     * @param question 问题
     * @param answer 答案
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-12 16:40
     */
    int checkAnswer(@Param("username")String username,@Param("question")String question,@Param("answer")String answer);

    int updatePasswordByUsername(@Param("username")String username,@Param("passwordNew")String passwordNew);

    int checkPassword(@Param(value="password")String password,@Param("userId")Integer userId);
}