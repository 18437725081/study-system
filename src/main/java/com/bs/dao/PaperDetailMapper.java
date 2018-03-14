package com.bs.dao;

import com.bs.pojo.PaperDetail;

/**
 * @author 张靖烽
 * @description 试卷详情DAO
 * @createtime 2018-03-14 14:40
 */
public interface PaperDetailMapper {
    /**
     * 根据主键删除试卷试题
     *
     * @param pkPaperDetail 试卷详情表主键
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:41
     */
    int deleteByPrimaryKey(Integer pkPaperDetail);

    /**
     * 新增试卷试题
     *
     * @param paperDetail 试卷详情类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:43
     */
    int insert(PaperDetail paperDetail);

    /**
     * 新增试卷试题
     *
     * @param paperDetail 试卷详情类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:43
     */
    int insertSelective(PaperDetail paperDetail);

    /**
     * 通过主键查询试卷详情
     *
     * @param pkPaperDetail 试卷详情主键
     * @return paperDetail
     * @author 张靖烽
     * @createtime 2018-03-14 14:44
     */
    PaperDetail selectByPrimaryKey(Integer pkPaperDetail);

    /**
     * 更新试卷详情
     *
     * @param paperDetail 试卷详情类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:44
     */
    int updateByPrimaryKeySelective(PaperDetail paperDetail);

    /**
     * 更新试卷详情
     *
     * @param paperDetail 试卷详情类
     * @return int
     * @author 张靖烽 
     * @createtime 2018-03-14 14:44
     */
    int updateByPrimaryKey(PaperDetail paperDetail);
}