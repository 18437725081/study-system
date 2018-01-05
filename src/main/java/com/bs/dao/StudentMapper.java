package com.bs.dao;

import com.bs.pojo.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer pkStudent);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer pkStudent);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}