package com.bs.dao;

import com.bs.pojo.Score;

public interface ScoreMapper {
    int deleteByPrimaryKey(Integer pkScore);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Integer pkScore);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);
}