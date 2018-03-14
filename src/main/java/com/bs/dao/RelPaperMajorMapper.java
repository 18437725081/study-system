package com.bs.dao;

import com.bs.pojo.RelPaperMajor;

public interface RelPaperMajorMapper {
    int deleteByPrimaryKey(Integer pkRelPaperMajor);

    int insert(RelPaperMajor record);

    int insertSelective(RelPaperMajor record);

    RelPaperMajor selectByPrimaryKey(Integer pkRelPaperMajor);

    int updateByPrimaryKeySelective(RelPaperMajor record);

    int updateByPrimaryKey(RelPaperMajor record);
}