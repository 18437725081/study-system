package com.bs.dao;

import com.bs.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 张靖烽
 * @description 学生DAO层
 * @createtime 2018-01-09 14:20
 */
public interface StudentMapper {

    /**
     * 根据主键删除学生
     *
     * @param pkStudent 学生主键
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-10 11:10
     */
    int deleteByPrimaryKey(Integer pkStudent);

    /**
     * 新增学生
     *
     * @param student 学生类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-10 11:11
     */
    int insert(Student student);

    /**
     * 新增学生
     *
     * @param student 学生类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-10 11:12
     */
    int insertSelective(Student student);

    /**
     * 通过主键查询学生
     *
     * @param pkStudent 学生主键
     * @return Student
     * @author 张靖烽
     * @createtime 2018-01-10 11:12
     */
    Student selectByPrimaryKey(Integer pkStudent);

    /**
     * 更新学生
     *
     * @param student 学生类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-10 11:12
     */
    int updateByPrimaryKeySelective(Student student);

    /**
     * 更新学生
     *
     * @param student 学生类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-10 11:23
     */
    int updateByPrimaryKey(Student student);

    /**
     * 查询所有学生信息
     *
     * @return List<Student>
     * @author 张靖烽
     * @createtime 2018-01-10 11:23
     */
    List<Student> selectAllStudent();

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
    Student login(@Param("username") String username, @Param("password") String password);

    /**
     * 获取问题
     *
     * @param username 用户名
     * @return Manager
     * @author 张靖烽
     * @createtime 2018-01-12 14:37
     */
    String selectQuestionByStudent(String username);
}