package com.bs.dao;

import com.bs.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生
 *
 * @author 暗香
 */
public interface StudentMapper {

    /**
     * 根据主键删除学生
     *
     * @param pkStudent 学生主键
     * @return int
     */
    int deleteByPrimaryKey(Integer pkStudent);

    /**
     * 新增学生
     *
     * @param student 学生类
     * @return int
     */
    int insert(Student student);

    /**
     * 新增学生
     *
     * @param student 学生类
     * @return int
     */
    int insertSelective(Student student);

    /**
     * 通过主键查询学生
     *
     * @param pkStudent 学生主键
     * @return Student
     */
    Student selectByPrimaryKey(Integer pkStudent);

    /**
     * 更新学生
     *
     * @param student 学生类
     * @return int
     */
    int updateByPrimaryKeySelective(Student student);

    /**
     * 更新学生
     *
     * @param student 学生类
     * @return int
     */
    int updateByPrimaryKey(Student student);

    /**
     * 查询所有学生信息
     *
     * @return List<Student>
     */
    List<Student> selectAllStudent();

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
    Student login(@Param("username") String username, @Param("password") String password);

    /**
     * 获取问题
     *
     * @param username 用户名
     * @return Manager
     */
    String selectQuestionByStudent(String username);

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
     * @param pkStudent 学生主键
     * @return int
     */
    int checkPassword(@Param(value = "password") String password, @Param("pkStudent") Integer pkStudent);

    /**
     * 检查用户名是否已存在
     *
     * @param username 用户名
     * @return int
     */
    int selectUsername(String username);

    /**
     * 查询学生
     *
     * @param student 学生类
     * @return List<Student>
     */
    List<Student> queryStudent(Student student);
}