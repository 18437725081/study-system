package com.bs.dao;

import com.bs.pojo.relPaperMajor;

public interface relPaperMajorMapper {
    int deleteByPrimaryKey(Integer pkRelPaperMajor);

    int insert(relPaperMajor record);

    int insertSelective(relPaperMajor record);

    relPaperMajor selectByPrimaryKey(Integer pkRelPaperMajor);

    int updateByPrimaryKeySelective(relPaperMajor record);

    int updateByPrimaryKey(relPaperMajor record);
}