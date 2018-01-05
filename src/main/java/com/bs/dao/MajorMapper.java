package com.bs.dao;

import com.bs.pojo.Major;

public interface MajorMapper {
    int deleteByPrimaryKey(Integer pkMajor);

    int insert(Major record);

    int insertSelective(Major record);

    Major selectByPrimaryKey(Integer pkMajor);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);
}