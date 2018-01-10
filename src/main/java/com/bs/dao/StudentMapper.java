package com.bs.dao;

import com.bs.pojo.Student;

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
}