package com.bs.dao;

import com.bs.pojo.Tests;

import java.util.List;

/**
 * @author 张靖烽
 * @description 试题DAO层
 * @createtime 2018-03-07 19:17
 */
public interface TestsMapper {
    /**
     * 根据主键删除试题
     *
     * @param pkTest 试题表主键
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-07 19:18
     */
    int deleteByPrimaryKey(Integer pkTest);

    /**
     * 新增试题
     *
     * @param tests 试题类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-07 19:18
     */
    int insert(Tests tests);

    /**
     * 新增试题
     *
     * @param tests 试题类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-07 19:18
     */
    int insertSelective(Tests tests);

    /**
     * 通过主键查询试题
     *
     * @param pkTest 试题主键
     * @return Tests
     * @author 张靖烽
     * @createtime 2018-03-07 19:18
     */
    Tests selectByPrimaryKey(Integer pkTest);

    /**
     * 更新试题
     *
     * @param tests 试题类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-07 19:18
     */
    int updateByPrimaryKeySelective(Tests tests);

    /**
     * 更新试题
     *
     * @param tests 试题类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-07 19:18
     */
    int updateByPrimaryKey(Tests tests);

    /**
     * 查询试题
     *
     * @param tests 试题类
     * @return Tests
     * @author 张靖烽
     * @createtime 2018-03-08 12:49
     */
    List<Tests> queryTests(Tests tests);

    /**
     * 查询我的试题
     *
     * @param tests 试题类
     * @return Tests
     * @author 张靖烽
     * @createtime 2018-03-08 12:49
     */
    List<Tests> queryMyTests(Tests tests);
}