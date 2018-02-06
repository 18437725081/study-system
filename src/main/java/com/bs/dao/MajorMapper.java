package com.bs.dao;

import com.bs.pojo.Major;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 张靖烽
 * @description 年级专业DAO层
 * @createtime 2018-01-10 14:13
 */
public interface MajorMapper {
    /**
     * 根据主键删除年级专业
     *
     * @param pkMajor 年级专业主键
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-10 14:13
     */
    int deleteByPrimaryKey(Integer pkMajor);

    /**
     * 新增年级专业
     *
     * @param major 年级专业类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-10 14:13
     */
    int insert(Major major);

    /**
     * 新增年级专业
     *
     * @param major 年级专业类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-10 14:13
     */
    int insertSelective(Major major);

    /**
     * 通过主键查询年级专业
     *
     * @param pkMajor 年级专业主键
     * @return major
     * @author 张靖烽
     * @createtime 2018-01-10 14:13
     */
    Major selectByPrimaryKey(Integer pkMajor);

    /**
     * 更新年级专业
     *
     * @param major 年级专业类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-10 14:13
     */
    int updateByPrimaryKeySelective(Major major);

    /**
     * 更新年级专业
     *
     * @param major 年级专业类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-10 14:13
     */
    int updateByPrimaryKey(Major major);

    /**
     * 查询专业信息
     *
     * @param major 专业类
     * @return List<Major>
     * @author 张靖烽
     * @createtime 2018-01-17 15:00
     */
    List<Major> queryMajor(Major major);

    /**
     * 获取年级列表
     *
     * @return List<String>
     * @author 张靖烽
     * @createtime 2018-02-06 19:02
     */
    List<String> getGrade();

    /**
     * 根据年级获取相应专业列表
     *
     * @param grade 年级
     * @return List<Major>
     * @author 张靖烽
     * @createtime 2018-02-06 19:03
     */
    List<Major> getMajor(String grade);

    /**
     * 根据专业主键查询专业信息
     *
     * @param pkMajorList 专业主键list
     * @return List<Major>
     * @author 张靖烽
     * @createtime 2018-02-06 19:05
     */
    List<Major> selectMajorByPk(@Param("pkMajorList")List<Integer> pkMajorList);

    /**
     * 查询是否存在该年级专业
     *
     * @param major 专业类
     * @return int
     * @author 张靖烽
     * @createtime 2018-02-06 19:06
     */
    int selectMajorCount(Major major);
}