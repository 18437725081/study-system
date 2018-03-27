package com.bs.controller;

import com.bs.common.Constant;
import com.bs.common.ResponseCode;
import com.bs.common.ServerResponse;
import com.bs.pojo.Teacher;
import com.bs.pojo.Tests;
import com.bs.service.TestsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author 张靖烽
 * @name TestsController
 * @description
 * @create 2018-03-07 19:12
 **/
@Controller
@RequestMapping("/tests/")
public class TestsController {

    @Autowired
    private TestsService testsService;

    private static final Logger log = LoggerFactory.getLogger(TestsController.class);

    /**
     * @author 张靖烽
     * @description 查询试题
     * @createtime 2018-03-07 19:23
     */
    @RequestMapping("queryTests.do")
    @ResponseBody
    public ServerResponse queryTests(HttpSession session, Tests tests,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        //判断登录
        Teacher teacher = (Teacher) session.getAttribute(Constant.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            tests.setFlag("Y");
            return testsService.queryTests(tests, pageNum, pageSize);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }


    /**
     * @author 张靖烽
     * @description 查询我的试题
     * @createtime 2018-03-08 12:39
     */
    @RequestMapping("queryMyTests.do")
    @ResponseBody
    public ServerResponse queryMyTests(HttpSession session, Tests tests,
                                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        //判断登录
        Teacher teacher = (Teacher) session.getAttribute(Constant.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            tests.setCreatedBy(teacher.getPkTeacher());
            return testsService.queryTests(tests, pageNum, pageSize);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }


    /**
     * @author 张靖烽
     * @description 获取单试题信息
     * @createtime 2018-03-07 19:29
     */
    @RequestMapping("getTestsInfo.do")
    @ResponseBody
    public ServerResponse getTestsInfo(HttpSession session, Integer pkTest) {
        //判断登录
        Teacher teacher = (Teacher) session.getAttribute(Constant.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            return testsService.getTestsInfo(pkTest);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    /**
     * @author 张靖烽
     * @description 新增试题
     * @createtime 2018-01-05 10:12
     */
    @RequestMapping("addTest.do")
    @ResponseBody
    public ServerResponse addTest(HttpSession session, Tests tests) {
        //判断登录
        Teacher teacher = (Teacher) session.getAttribute(Constant.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            return testsService.addTest(tests, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    /**
     * @author 张靖烽
     * @description 修改试题状态
     * @createtime 2018-01-05 10:12
     */
    @RequestMapping("modifyTestFlag.do")
    @ResponseBody
    public ServerResponse modifyTestFlag(HttpSession session, Integer pkTest, String flag) {
        //判断登录
        Teacher teacher = (Teacher) session.getAttribute(Constant.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            return testsService.modifyTestFlag(pkTest, flag, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    /**
     * @author 张靖烽
     * @description 查询科目列表
     * @createtime 2018-03-08 13:41
     */
    @RequestMapping("selectSubjectList.do")
    @ResponseBody
    public ServerResponse selectSubjectList(HttpSession session) {
        //判断登录
        Teacher teacher = (Teacher) session.getAttribute(Constant.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            return testsService.selectSubjectList();
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }


    /**
     * @author 张靖烽
     * @description 查询试卷试题
     * @createtime 2018-03-08 13:41
     */
    @RequestMapping("selectPaperTests.do")
    @ResponseBody
    public ServerResponse selectPaperTests(HttpSession session,Integer fkPaper) {
        //判断登录
        Teacher teacher = (Teacher) session.getAttribute(Constant.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            return testsService.selectPaperTests(fkPaper);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }
}
