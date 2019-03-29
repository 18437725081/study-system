package com.bs.service;

import com.bs.common.Constant;
import com.bs.common.ServerResponse;
import com.bs.dao.TeacherMapper;
import com.bs.dao.TestsMapper;
import com.bs.pojo.Teacher;
import com.bs.pojo.Tests;
import com.bs.util.TimeUtil;
import com.bs.vo.TestVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张靖烽
 * @name TestsService
 * @description
 * @create 2018-03-07 19:13
 **/
@Service
public class TestsService {

    @Autowired
    private TestsMapper testsMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 查询试题
     *
     * @param tests
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse queryTests(Tests tests, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Tests> list = testsMapper.queryTests(tests);
        List<TestVO> lists = Lists.newArrayList();
        for (Tests t : list) {
            lists.add(setTestVO(t));
        }
        PageInfo<TestVO> pageInfo = new PageInfo<>(lists);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /**
     * 获取单试题信息
     *
     * @param pkTest
     * @return
     */
    public ServerResponse getTestsInfo(Integer pkTest) {
        if (pkTest == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        Tests tests = testsMapper.selectByPrimaryKey(pkTest);
        if (tests != null) {
            TestVO testVO = setTestVO(tests);
            return ServerResponse.createBySuccess(testVO);
        }
        return ServerResponse.createByErrorMessage("试题不存在或已被删除");
    }

    /**
     * 新增试题
     *
     * @param tests
     * @param teacher
     * @return
     */
    public ServerResponse addTest(Tests tests, Teacher teacher) {
        if (tests != null) {
            tests.setCreatedBy(teacher.getPkTeacher());
            tests.setLastUpdatedBy(teacher.getPkTeacher());
            tests.setFlag("Y");
            int result = testsMapper.insert(tests);
            if (result > 0) {
                return ServerResponse.createBySuccessMessage("新增成功");
            }
            return ServerResponse.createByErrorMessage("新增失败");
        }
        return ServerResponse.createByErrorMessage("参数不正确");
    }

    /**
     * 修改试题状态
     *
     * @param pkTest
     * @param flag
     * @param teacher
     * @return
     */
    public ServerResponse modifyTestFlag(Integer pkTest, String flag, Teacher teacher) {
        if (pkTest != null && flag != null) {
            int pkTeacher = testsMapper.selectCreatedByPkTest(pkTest);
            if (pkTeacher == teacher.getPkTeacher()) {
                Tests tests = new Tests();
                tests.setPkTest(pkTest);
                tests.setFlag(flag);
                tests.setLastUpdatedBy(teacher.getPkTeacher());
                int result = testsMapper.updateByPrimaryKeySelective(tests);
                if (result > 0) {
                    return ServerResponse.createBySuccessMessage("修改成功");
                }
                return ServerResponse.createByErrorMessage("试题不存在或已被删除");
            }
            return ServerResponse.createByErrorMessage("无权限修改此试题");
        }
        return ServerResponse.createByErrorMessage("参数不正确");
    }

    /**
     * 查询科目列表
     *
     * @return
     */
    public ServerResponse selectSubjectList() {
        return ServerResponse.createBySuccess(testsMapper.selectSubjectList());
    }

    /**
     * 设置TestVO返回对象
     *
     * @param tests 试卷
     * @return
     */
    private TestVO setTestVO(Tests tests) {
        TestVO testVO = new TestVO();
        testVO.setPkTest(tests.getPkTest());
        if (Constant.TestType.TEST_CHOICE.equals(tests.getTestType())) {
            testVO.setTestType("选择题");
        } else if (Constant.TestType.TEST_JUDGE.equals(tests.getTestType())) {
            testVO.setTestType("判断题");
        } else if (Constant.TestType.TEST_FILL.equals(tests.getTestType())) {
            testVO.setTestType("填空题");
        } else {
            testVO.setTestType("其他");
        }
        testVO.setTestSubject(tests.getTestSubject());
        testVO.setTestTitle(tests.getTestTitle());
        testVO.setTestContent(tests.getTestContent());
        testVO.setTestAnswer(tests.getTestAnswer());
        testVO.setTestAnalyze(tests.getTestAnalyze());
        if (Constant.FLAG_Y.equals(tests.getFlag())) {
            testVO.setFlag("可以使用");
        } else {
            testVO.setFlag("无法使用");
        }
        testVO.setLastUpdatedTime(TimeUtil.dateToStr(tests.getLastUpdatedTime()));
        String createdBy = teacherMapper.selectTeacherName(tests.getCreatedBy());
        testVO.setCreatedBy(createdBy);
        return testVO;
    }

}
