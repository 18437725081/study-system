package com.bs.dao;

import com.bs.pojo.Paper;

public interface PaperMapper {
    int deleteByPrimaryKey(Integer pkPaper);

    int insert(Paper record);

    int insertSelective(Paper record);

    Paper selectByPrimaryKey(Integer pkPaper);

    int updateByPrimaryKeySelective(Paper record);

    int updateByPrimaryKey(Paper record);
}