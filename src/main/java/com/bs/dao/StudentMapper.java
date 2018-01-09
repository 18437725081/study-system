package com.bs.dao;

import com.bs.pojo.Student;

import java.util.List;

/**
 * @author 张靖烽
 * @description 学生DAO层
 * @createtime 2018-01-09 14:20
 */
public interface StudentMapper {
    int deleteByPrimaryKey(Integer pkStudent);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer pkStudent);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> selectAllTeacher();
}