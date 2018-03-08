package com.bs.service;

import com.bs.common.ServerResponse;
import com.bs.dao.TestsMapper;
import com.bs.pojo.Teacher;
import com.bs.pojo.Tests;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    /**
     * @author 张靖烽
     * @description 查询试题
     * @createtime 2018-03-07 19:33
     */
    public ServerResponse queryTests(Tests tests, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Tests> list = testsMapper.queryTests(tests);
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setList(list);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /**
     * @author 张靖烽
     * @description 查询我的试题
     * @createtime 2018-03-08 12:42
     */
    public ServerResponse queryMyTests(Tests tests, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Tests> list = testsMapper.queryMyTests(tests);
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setList(list);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /**
     * @author 张靖烽
     * @description 获取单试题信息
     * @createtime 2018-03-07 19:33
     */
    public ServerResponse getTestsInfo(Integer pkTest) {
        if (pkTest == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        Tests tests = testsMapper.selectByPrimaryKey(pkTest);
        if (tests != null) {
            return ServerResponse.createBySuccess(tests);
        }
        return ServerResponse.createByErrorMessage("试题不存在或已被删除");
    }

    /**
     * @author 张靖烽
     * @description 新增试题
     * @createtime 2018-03-08 13:32
     */
    public ServerResponse addTest(Tests tests, Teacher teacher) {
        if (tests != null){
            tests.setCreatedBy(teacher.getPkTeacher());
            tests.setLastUpdatedBy(teacher.getPkTeacher());
            int result = testsMapper.insert(tests);
            if (result > 0) {
                return ServerResponse.createBySuccessMessage("新增成功");
            }
            return ServerResponse.createByErrorMessage("新增失败");
        }
        return ServerResponse.createByErrorMessage("参数不正确");
    }

    /**
     * @author 张靖烽
     * @description 修改试题状态
     * @createtime 2018-03-08 13:38
     */
    public ServerResponse modifyTestFlag(Integer pkTest, String flag, Teacher teacher) {
        if (pkTest != null && flag !=null){
            Tests tests = new Tests();
            tests.setPkTest(pkTest);
            tests.setFlag(flag);
            tests.setLastUpdatedBy(teacher.getPkTeacher());
            int result = testsMapper.updateByPrimaryKeySelective(tests);
            if (result > 0) {
                return ServerResponse.createBySuccessMessage("修改成功");
            }
            return ServerResponse.createByErrorMessage("修改失败");
        }
        return ServerResponse.createByErrorMessage("参数不正确");
    }

    /**
     * @author 张靖烽
     * @description 查询科目列表
     * @createtime 2018-03-08 13:42
     */
    public ServerResponse selectSubjectList() {
        List<String> list = testsMapper.selectSubjectList();
        return ServerResponse.createBySuccess(list);
    }
}
