package com.bs.dao;

import com.bs.pojo.RelTeacherMajor;

public interface RelTeacherMajorMapper {
    int deleteByPrimaryKey(Integer pkRelTeacherMajor);

    int insert(RelTeacherMajor record);

    int insertSelective(RelTeacherMajor record);

    RelTeacherMajor selectByPrimaryKey(Integer pkRelTeacherMajor);

    int updateByPrimaryKeySelective(RelTeacherMajor record);

    int updateByPrimaryKey(RelTeacherMajor record);
}