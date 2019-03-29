package com.bs.dao;

import com.bs.pojo.Tests;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 试卷
 *
 * @author 暗香
 */
public interface TestsMapper {
    /**
     * 根据主键删除试题
     *
     * @param pkTest 试题表主键
     * @return int
     */
    int deleteByPrimaryKey(Integer pkTest);

    /**
     * 新增试题
     *
     * @param tests 试题类
     * @return int
     */
    int insert(Tests tests);

    /**
     * 新增试题
     *
     * @param tests 试题类
     * @return int
     */
    int insertSelective(Tests tests);

    /**
     * 通过主键查询试题
     *
     * @param pkTest 试题主键
     * @return Tests
     */
    Tests selectByPrimaryKey(Integer pkTest);

    /**
     * 更新试题
     *
     * @param tests 试题类
     * @return int
     */
    int updateByPrimaryKeySelective(Tests tests);

    /**
     * 更新试题
     *
     * @param tests 试题类
     * @return int
     */
    int updateByPrimaryKey(Tests tests);

    /**
     * 查询试题
     *
     * @param tests 试题类
     * @return Tests
     */
    List<Tests> queryTests(Tests tests);

    /**
     * 查询科目列表
     *
     * @return String
     */
    List<String> selectSubjectList();

    /**
     * 查询创建人
     *
     * @param pkTest 试题主键
     * @return int
     */
    int selectCreatedByPkTest(Integer pkTest);

    String selectType(Integer fkTests);

    List<Tests> randomOptionTests(@Param("subject") String subject, @Param("optionNumber") Integer optionNumber, @Param("type") String type);

    int selectTestsNumber(@Param("type") String type, @Param("subject") String subject);

    int checkAnswer(@Param("pkTest") String pkTest, @Param("answer") String answer);
}