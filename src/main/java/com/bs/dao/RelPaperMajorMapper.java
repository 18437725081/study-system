package com.bs.dao;

import com.bs.pojo.RelPaperMajor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 关联（试卷&专业）DAO
 *
 * @author 暗香
 */
public interface RelPaperMajorMapper {
    /**
     * 根据主键删除
     *
     * @param pkRelPaperMajor 主键
     * @return int
     */
    int deleteByPrimaryKey(Integer pkRelPaperMajor);

    /**
     * 新增关联
     *
     * @param relPaperMajor 关联类
     * @return int
     */
    int insert(RelPaperMajor relPaperMajor);

    /**
     * 新增关联
     *
     * @param relPaperMajor 关联类
     * @return int
     */
    int insertSelective(RelPaperMajor relPaperMajor);

    /**
     * 通过主键查询
     *
     * @param pkRelPaperMajor 关联主键
     * @return relPaperMajor类对象
     */
    RelPaperMajor selectByPrimaryKey(Integer pkRelPaperMajor);

    /**
     * 更新
     *
     * @param relPaperMajor 关联类
     * @return int
     */
    int updateByPrimaryKeySelective(RelPaperMajor relPaperMajor);

    /**
     * 更新
     *
     * @param relPaperMajor 关联类
     * @return int
     */
    int updateByPrimaryKey(RelPaperMajor relPaperMajor);

    List<RelPaperMajor> selectByFkMajor(Integer fkMajor);

    int selectCount(@Param("pkPaper") Integer pkPaper, @Param("fkMajor") Integer fkMajor);
}