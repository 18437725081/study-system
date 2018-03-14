package com.bs.dao;

import com.bs.pojo.RelTeacherMajor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 张靖烽
 * @description 教师和专业关联DAO层
 * @createtime 2018-01-10 14:16
 */
public interface RelTeacherMajorMapper {
    /**
     * 根据主键删除
     *
     * @param pkRelTeacherMajor 主键
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-10 14:17
     */
    int deleteByPrimaryKey(Integer pkRelTeacherMajor);

    /**
     * 新增关联
     *
     * @param relTeacherMajor 关联类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-10 14:17
     */
    int insert(RelTeacherMajor relTeacherMajor);

    /**
     * 新增关联
     *
     * @param relTeacherMajor 关联类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-10 14:17
     */
    int insertSelective(RelTeacherMajor relTeacherMajor);

    /**
     * 通过主键查询
     *
     * @param pkRelTeacherMajor 关联主键
     * @return RelTeacherMajor类对象
     * @author 张靖烽
     * @createtime 2018-01-10 14:17
     */
    RelTeacherMajor selectByPrimaryKey(Integer pkRelTeacherMajor);

    /**
     * 更新
     *
     * @param relTeacherMajor 关联类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-10 14:17
     */
    int updateByPrimaryKeySelective(RelTeacherMajor relTeacherMajor);

    /**
     * 更新
     *
     * @param relTeacherMajor 关联类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-10 14:17
     */
    int updateByPrimaryKey(RelTeacherMajor relTeacherMajor);

    /**
     * 通过教师查询关联的专业外键
     *
     * @param pkTeacher 教师外键
     * @return List<Integer>
     * @author 张靖烽
     * @createtime 2018-01-10 14:20
     */
    List<Integer> selectFkMajor(Integer pkTeacher);

    /**
     * 删除
     *
     * @param pkTeacher 教师外键
     * @param pkMajor 专业外键
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-10 14:20
     */
    int delete(@Param("pkTeacher") Integer pkTeacher, @Param("pkMajor") Integer pkMajor);

    /**
     * 通过教师查询已关联的专业主键list
     *
     * @param pkTeacher 教师外键
     * @return List<Integer>
     * @author 张靖烽
     * @createtime 2018-02-06 19:17
     */
    List<Integer> selectFkMajorList(String pkTeacher);

    /**
     * 查询教师是否和专业关联
     *
     * @param pkTeacher 教师外键
     * @param pkMajor 专业外键
     * @return int
     * @author 张靖烽
     * @createtime 2018-02-06 19:18
     */
    int selectRelCount(@Param("pkTeacher")Integer pkTeacher, @Param("pkMajor")Integer pkMajor);
}