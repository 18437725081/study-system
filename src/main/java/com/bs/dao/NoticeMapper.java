package com.bs.dao;

import com.bs.pojo.Notice;

import java.util.List;

/**
 * 通知类
 *
 * @author 暗香
 */
public interface NoticeMapper {

    /**
     * 根据主键删除通知
     *
     * @param pkNotice 通知主键
     * @return int
     */
    int deleteByPrimaryKey(Integer pkNotice);

    /**
     * 新增通知
     *
     * @param notice 通知类
     * @return int
     */
    int insert(Notice notice);

    /**
     * 新增通知
     *
     * @param notice 通知类
     * @return int
     */
    int insertSelective(Notice notice);

    /**
     * 通过主键查询通知
     *
     * @param pkNotice 通知主键
     * @return Notice类对象
     */
    Notice selectByPrimaryKey(Integer pkNotice);

    /**
     * 更新通知
     *
     * @param notice 通知类
     * @return int
     */
    int updateByPrimaryKeySelective(Notice notice);

    /**
     * 更新通知
     *
     * @param notice 通知类
     * @return int
     */
    int updateByPrimaryKey(Notice notice);

    /**
     * 查询所有有效通知
     *
     * @return List<String>
     */
    List<String> selectNotice();

    /**
     * 查询
     *
     * @param notice 通知类
     * @return List<Notice>
     */
    List<Notice> queryNotice(Notice notice);
}