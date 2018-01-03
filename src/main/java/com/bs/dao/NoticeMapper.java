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
     * @author 张靖烽
     * @param pkNotice 通知主键
     * @return int
     * @createtime 2017-12-21 9:56
     */
    int deleteByPrimaryKey(Integer pkNotice);

    /**
     * 新增通知
     *
     * @author 张靖烽
     * @param record 通知类
     * @return int
     * @createtime 2017-12-21 9:59
     */
    int insert(Notice record);

    /**
     * 新增通知
     *
     * @author 张靖烽
     * @param record 通知类
     * @return int
     * @createtime 2017-12-21 10:00
     */
    int insertSelective(Notice record);

    /**
     * 通过主键查询通知
     *
     * @author 张靖烽
     * @param pkNotice 通知主键
     * @return Notice类对象
     * @createtime 2017-12-21 10:01
     */
    Notice selectByPrimaryKey(Integer pkNotice);

    /**
     * 更新通知
     *
     * @author 张靖烽
     * @param record 通知类
     * @return int
     * @createtime 2017-12-21 10:04
     */
    int updateByPrimaryKeySelective(Notice record);

    /**
     * 更新通知
     *
     * @author 张靖烽
     * @param record 通知类
     * @return int
     * @createtime 2017-12-21 10:05
     */
    int updateByPrimaryKey(Notice record);

    /**
     * 查询所有有效通知
     *
     * @author 张靖烽
     * @return List<String>
     * @createtime 2017-12-21 10:05
     */
    List<String> selectNotice();

    /**
     * 查询所有通知
     *
     * @author 张靖烽
     * @return List<Notice>
     * @createtime 2017-12-29 11:12
     */
    List<Notice> selectAllNotice();

}