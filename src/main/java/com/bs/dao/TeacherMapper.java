package com.bs.dao;

import com.bs.pojo.Teacher;

import java.util.List;

/**
 * @author 张靖烽
 * @description 教师DAO层
 * @createtime 2018-01-03 20:48
 */
public interface TeacherMapper {
    /**
     * 根据主键删除教师
     *
     * @param pkTeacher 教师主键
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-03 20:49
     */
    int deleteByPrimaryKey(Integer pkTeacher);

    /**
     * 新增教师
     *
     * @param record 教师类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-03 20:49
     */
    int insert(Teacher record);

    /**
     * 新增教师
     *
     * @param record 教师类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-03 20:49
     */
    int insertSelective(Teacher record);

    /**
     * 通过主键查询教师
     *
     * @param pkTeacher 教师主键
     * @return Teacher
     * @author 张靖烽
     * @createtime 2018-01-03 20:49
     */
    Teacher selectByPrimaryKey(Integer pkTeacher);

    /**
     * 更新教师
     *
     * @param record 教师类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-03 20:49
     */
    int updateByPrimaryKeySelective(Teacher record);

    /**
     * 更新教师
     *
     * @param record 教师类
     * @return int
     * @author 张靖烽
     * @createtime 2018-01-03 20:49
     */
    int updateByPrimaryKey(Teacher record);

    /**
     * 查询所有教师
     *
     * @return List<Teacher>
     * @author 张靖烽
     * @createtime 2017-12-29 11:12
     */
    List<Teacher> selectAllTeacher();
}