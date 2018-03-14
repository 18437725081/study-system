package com.bs.dao;

import com.bs.pojo.RelPaperMajor;

/**
 * @author 张靖烽
 * @description 关联（试卷&专业）DAO
 * @createtime 2018-03-14 14:40
 */
public interface RelPaperMajorMapper {
    /**
     * 根据主键删除
     *
     * @param pkRelPaperMajor 主键
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:41
     */
    int deleteByPrimaryKey(Integer pkRelPaperMajor);

    /**
     * 新增关联
     *
     * @param relPaperMajor 关联类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:52
     */
    int insert(RelPaperMajor relPaperMajor);

    /**
     * 新增关联
     *
     * @param relPaperMajor 关联类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:52
     */
    int insertSelective(RelPaperMajor relPaperMajor);

    /**
     * 通过主键查询
     *
     * @param pkRelPaperMajor 关联主键
     * @return relPaperMajor类对象
     * @author 张靖烽 
     * @createtime 2018-03-14 14:52
     */
    RelPaperMajor selectByPrimaryKey(Integer pkRelPaperMajor);

    /**
     * 更新
     *
     * @param relPaperMajor 关联类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:52
     */
    int updateByPrimaryKeySelective(RelPaperMajor relPaperMajor);

    /**
     * 更新
     *
     * @param relPaperMajor 关联类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 14:53
     */
    int updateByPrimaryKey(RelPaperMajor relPaperMajor);
}