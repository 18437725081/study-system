package com.bs.dao;

import com.bs.pojo.PaperDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 试卷详情
 *
 * @author 暗香
 */
public interface PaperDetailMapper {
    /**
     * 根据主键删除试卷试题
     *
     * @param pkPaperDetail 试卷详情表主键
     * @return int
     */
    int deleteByPrimaryKey(Integer pkPaperDetail);

    /**
     * 新增试卷试题
     *
     * @param paperDetail 试卷详情类
     * @return int
     */
    int insert(PaperDetail paperDetail);

    /**
     * 新增试卷试题
     *
     * @param paperDetail 试卷详情类
     * @return int
     */
    int insertSelective(PaperDetail paperDetail);

    /**
     * 通过主键查询试卷详情
     *
     * @param pkPaperDetail 试卷详情主键
     * @return paperDetail
     */
    PaperDetail selectByPrimaryKey(Integer pkPaperDetail);

    /**
     * 更新试卷详情
     *
     * @param paperDetail 试卷详情类
     * @return int
     */
    int updateByPrimaryKeySelective(PaperDetail paperDetail);

    /**
     * 更新试卷详情
     *
     * @param paperDetail 试卷详情类
     * @return int
     */
    int updateByPrimaryKey(PaperDetail paperDetail);

    List<PaperDetail> selectPaperDetailByPkPaper(Integer pkPaper);

    int deleteTestsFromPaper(@Param("fkTest") Integer fkTest, @Param("fkPaper") Integer fkPaper);

    int emptyTestsFromPaper(Integer fkPaper);

    int selectRepeat(@Param("fkPaper") Integer fkPaper, @Param("fkTests") Integer fkTests);

    List<Integer> selectPaperTests(Integer fkPaper);
}