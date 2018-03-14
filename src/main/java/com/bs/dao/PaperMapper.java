package com.bs.dao;

import com.bs.pojo.Paper;

/**
 * @author 张靖烽
 * @description 试卷DAO
 * @createtime 2018-03-14 14:40
 */
public interface PaperMapper {
    /**
     * 根据主键删除试卷
     *
     * @param pkPaper 试卷主键
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:41
     */
    int deleteByPrimaryKey(Integer pkPaper);

    /**
     * 新增试卷
     *
     * @param paper 试卷类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:49
     */
    int insert(Paper paper);

    /**
     * 新增试卷
     *
     * @param paper 试卷类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:49
     */
    int insertSelective(Paper paper);

    /**
     * 通过主键查询试卷
     *
     * @param pkPaper 试卷主键
     * @return paper
     * @author 张靖烽
     * @createtime 2018-03-14 14:49
     */
    Paper selectByPrimaryKey(Integer pkPaper);

    /**
     * 更新试卷
     *
     * @param paper 试卷类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:49
     */
    int updateByPrimaryKeySelective(Paper paper);

    /**
     * 更新试卷
     *
     * @param paper 试卷类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:49
     */
    int updateByPrimaryKey(Paper paper);
}