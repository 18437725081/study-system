package com.bs.dao;

import com.bs.pojo.Notice;

import java.util.List;

/**
 * @author 张靖烽
 * @description 通知dao层
 * @createtime 2017-12-21 9:55
 */
public interface NoticeMapper {
    /**
     * 根据主键删除通知
     *
     * @param pkNotice 通知主键
     * @return int
     * @author 张靖烽
     * @createtime 2017-12-21 9:56
     */
    int deleteByPrimaryKey(Integer pkNotice);

    /**
     * 新增通知
     *
     * @param notice 通知类
     * @return int
     * @author 张靖烽
     * @createtime 2017-12-21 9:59
     */
    int insert(Notice notice);

    /**
     * 新增通知
     *
     * @param notice 通知类
     * @return int
     * @author 张靖烽
     * @createtime 2017-12-21 10:00
     */
    int insertSelective(Notice notice);

    /**
     * 通过主键查询通知
     *
     * @param pkNotice 通知主键
     * @return Notice类对象
     * @author 张靖烽
     * @createtime 2017-12-21 10:01
     */
    Notice selectByPrimaryKey(Integer pkNotice);

    /**
     * 更新通知
     *
     * @param notice 通知类
     * @return int
     * @author 张靖烽
     * @createtime 2017-12-21 10:04
     */
    int updateByPrimaryKeySelective(Notice notice);

    /**
     * 更新通知
     *
     * @param notice 通知类
     * @return int
     * @author 张靖烽
     * @createtime 2017-12-21 10:05
     */
    int updateByPrimaryKey(Notice notice);

    /**
     * 查询所有有效通知
     *
     * @return List<String>
     * @author 张靖烽
     * @createtime 2017-12-21 10:05
     */
    List<String> selectNotice();

    /**
     * 查询
     *
     * @param notice 通知类
     * @return List<Notice>
     * @author 张靖烽
     * @createtime 2017-12-29 11:12
     */
    List<Notice> queryNotice(Notice notice);
}