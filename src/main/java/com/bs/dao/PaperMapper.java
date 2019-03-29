package com.bs.dao;

import com.bs.pojo.Paper;
import com.bs.pojo.Tests;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 试卷
 *
 * @author 暗香
 */
public interface PaperMapper {
    /**
     * 根据主键删除试卷
     *
     * @param pkPaper 试卷主键
     * @return int
     */
    int deleteByPrimaryKey(Integer pkPaper);

    /**
     * 新增试卷
     *
     * @param paper 试卷类
     * @return int
     */
    int insert(Paper paper);

    /**
     * 新增试卷
     *
     * @param paper 试卷类
     * @return int
     */
    int insertSelective(Paper paper);

    /**
     * 通过主键查询试卷
     *
     * @param pkPaper 试卷主键
     * @return paper
     */
    Paper selectByPrimaryKey(Integer pkPaper);

    /**
     * 更新试卷
     *
     * @param paper 试卷类
     * @return int
     */
    int updateByPrimaryKeySelective(Paper paper);

    /**
     * 更新试卷
     *
     * @param paper 试卷类
     * @return int
     */
    int updateByPrimaryKey(Paper paper);

    /**
     * 查询试卷
     *
     * @param paper 试卷类
     * @return int
     */
    List<Paper> queryPaper(Paper paper);

    /**
     * 查询创建人
     *
     * @param pkPaper 试卷主键
     * @return int
     */
    Integer selectCreatedByPkPaper(@Param("pkPaper") Integer pkPaper);

    /**
     * 查询公开状态
     *
     * @param pkPaper 试卷主键
     * @return String
     */
    String selectPublicFlag(Integer pkPaper);

    String selectEditFlag(Integer fkPaper);

    int selectPaperName(String paperName);

    Integer selectByPaperName(String paperName);
}