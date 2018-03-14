package com.bs.dao;

import com.bs.pojo.PaperDetail;

public interface PaperDetailMapper {
    int deleteByPrimaryKey(Integer pkPaperDetail);

    int insert(PaperDetail record);

    int insertSelective(PaperDetail record);

    PaperDetail selectByPrimaryKey(Integer pkPaperDetail);

    int updateByPrimaryKeySelective(PaperDetail record);

    int updateByPrimaryKey(PaperDetail record);
}