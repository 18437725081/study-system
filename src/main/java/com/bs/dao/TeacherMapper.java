package com.bs.dao;

import com.bs.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教师
 *
 * @author 暗香
 */
public interface TeacherMapper {
    /**
     * 根据主键删除教师
     *
     * @param pkTeacher 教师主键
     * @return int
     */
    int deleteByPrimaryKey(Integer pkTeacher);

    /**
     * 新增教师
     *
     * @param teacher 教师类
     * @return int
     */
    int insert(Teacher teacher);

    /**
     * 新增教师
     *
     * @param teacher 教师类
     * @return int
     */
    int insertSelective(Teacher teacher);

    /**
     * 通过主键查询教师
     *
     * @param pkTeacher 教师主键
     * @return Teacher
     */
    Teacher selectByPrimaryKey(Integer pkTeacher);

    /**
     * 更新教师
     *
     * @param teacher 教师类
     * @return int
     */
    int updateByPrimaryKeySelective(Teacher teacher);

    /**
     * 更新教师
     *
     * @param teacher 教师类
     * @return int
     */
    int updateByPrimaryKey(Teacher teacher);

    /**
     * 检查用户名是否存在
     *
     * @param username 用户名
     * @return int
     */
    int checkUsername(String username);

    /**
     * 登录，检查用户名密码是否正确
     *
     * @param username 用户名
     * @param password 密码
     * @return Manager
     */
    Teacher login(@Param("username") String username, @Param("password") String password);

    /**
     * 获取问题
     *
     * @param username 用户名
     * @return Manager
     */
    String selectQuestionByTeacher(String username);

    /**
     * 校验答案
     *
     * @param username 用户名
     * @param question 问题
     * @param answer   答案
     * @return int
     */
    int checkAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    /**
     * 修改密码
     *
     * @param username    用户名
     * @param passwordNew 密码
     * @return int
     */
    int updatePasswordByUsername(@Param("username") String username, @Param("passwordNew") String passwordNew);

    /**
     * 校验密码归属
     *
     * @param password  密码
     * @param pkTeacher 教师主键
     * @return int
     */
    int checkPassword(@Param(value = "password") String password, @Param("pkTeacher") Integer pkTeacher);

    /**
     * 检查用户名是否已存在
     *
     * @param username 用户名
     * @return int
     */
    int selectUsername(String username);

    /**
     * 查询教师
     *
     * @param teacher 教师类
     * @return List<Teacher>
     */
    List<Teacher> queryTeacher(Teacher teacher);

    /**
     * 查询教师姓名
     *
     * @param pkTeacher 教师主键
     * @return String
     */
    String selectTeacherName(Integer pkTeacher);
}