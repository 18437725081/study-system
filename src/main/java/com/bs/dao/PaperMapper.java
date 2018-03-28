package com.bs.dao;

import com.bs.pojo.Paper;
import com.bs.pojo.Tests;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 查询试卷
     *
     * @param paper 试卷类
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 17:30
     */
    List<Paper> queryPaper(Paper paper);

    /**
     * 查询创建人
     *
     * @param pkPaper 试卷主键
     * @return int
     * @author 张靖烽
     * @createtime 2018-03-14 19:00
     */
    Integer selectCreatedByPkPaper(@Param("pkPaper") Integer pkPaper);

    /**
     * 查询公开状态
     *
     * @param pkPaper 试卷主键
     * @return String
     * @author 张靖烽
     * @createtime 2018-03-14 20:29
     */
    String selectPublicFlag(Integer pkPaper);

    String selectEditFlag(Integer fkPaper);

    int selectPaperName(String paperName);

    Integer selectByPaperName(String paperName);
}