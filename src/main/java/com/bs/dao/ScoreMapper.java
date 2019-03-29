package com.bs.dao;

import com.bs.pojo.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 成绩
 *
 * @author 暗香
 */
public interface ScoreMapper {
    /**
     * 根据主键删除成绩
     *
     * @param pkScore 成绩主键
     * @return int
     */
    int deleteByPrimaryKey(Integer pkScore);

    /**
     * 新增成绩
     *
     * @param score 成绩类
     * @return int
     */
    int insert(Score score);

    /**
     * 新增成绩
     *
     * @param score 成绩类
     * @return int
     */
    int insertSelective(Score score);

    /**
     * 通过主键查询成绩
     *
     * @param pkScore 成绩主键
     * @return score
     */
    Score selectByPrimaryKey(Integer pkScore);

    /**
     * 更新成绩
     *
     * @param score 成绩类
     * @return int
     */
    int updateByPrimaryKeySelective(Score score);

    /**
     * 更新成绩
     *
     * @param score 成绩类
     * @return int
     */
    int updateByPrimaryKey(Score score);

    int selectScoreCount(@Param("pkStudent") Integer pkStudent, @Param("fkPaper") Integer fkPaper);

    List<Score> StudentQueryScore(Integer pkStudent);
}