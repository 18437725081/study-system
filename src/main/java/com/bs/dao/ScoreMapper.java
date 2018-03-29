package com.bs.dao;

import com.bs.pojo.Score;
import org.apache.ibatis.annotations.Param;

/**
 * @author 张靖烽
 * @description 成绩DAO
 * @createtime 2018-03-14 14:41
 */
public interface ScoreMapper {
    /**
     * 根据主键删除成绩
     *
     * @param pkScore 成绩主键
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:41
     */
    int deleteByPrimaryKey(Integer pkScore);

    /**
     * 新增成绩
     *
     * @param score 成绩类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:47
     */
    int insert(Score score);

    /**
     * 新增成绩
     *
     * @param score 成绩类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:47
     */
    int insertSelective(Score score);

    /**
     * 通过主键查询成绩
     *
     * @param pkScore 成绩主键
     * @return score
     * @author 张靖烽
     * @createtime 2018-03-14 14:47
     */
    Score selectByPrimaryKey(Integer pkScore);

    /**
     * 更新成绩
     *
     * @param score 成绩类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:47
     */
    int updateByPrimaryKeySelective(Score score);

    /**
     * 更新成绩
     *
     * @param score 成绩类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:47
     */
    int updateByPrimaryKey(Score score);

    int selectScoreCount(@Param("pkStudent") Integer pkStudent, @Param("fkPaper") Integer fkPaper);
}