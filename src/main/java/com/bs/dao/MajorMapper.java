package com.bs.dao;

import com.bs.pojo.Major;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 年级专业
 *
 * @author 暗香
 */
public interface MajorMapper {

    /**
     * 根据主键删除年级专业
     *
     * @param pkMajor 年级专业主键
     */
    int deleteByPrimaryKey(Integer pkMajor);

    /**
     * 新增年级专业
     *
     * @param major 年级专业类
     * @return int
     */
    int insert(Major major);

    /**
     * 新增年级专业
     *
     * @param major 年级专业类
     * @return int
     */
    int insertSelective(Major major);

    /**
     * 通过主键查询年级专业
     *
     * @param pkMajor 年级专业主键
     * @return major
     */
    Major selectByPrimaryKey(Integer pkMajor);

    /**
     * 更新年级专业
     *
     * @param major 年级专业类
     * @return int
     */
    int updateByPrimaryKeySelective(Major major);

    /**
     * 更新年级专业
     *
     * @param major 年级专业类
     * @return int
     */
    int updateByPrimaryKey(Major major);

    /**
     * 查询专业信息
     *
     * @param major 专业类
     * @return List<Major>
     */
    List<Major> queryMajor(Major major);

    /**
     * 获取年级列表
     *
     * @return List<String>
     */
    List<String> getGrade();

    /**
     * 根据年级获取相应专业列表
     *
     * @param grade 年级
     * @return List<Major>
     */
    List<Major> getMajor(String grade);

    /**
     * 根据专业主键查询专业信息
     *
     * @param pkMajorList 专业主键list
     * @return List<Major>
     */
    List<Major> selectMajorByPk(@Param("pkMajorList") List<Integer> pkMajorList);

    /**
     * 查询是否存在该年级专业
     *
     * @param major 专业类
     * @return int
     */
    int selectMajorCount(Major major);
}