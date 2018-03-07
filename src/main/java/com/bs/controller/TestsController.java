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
            return testsService.queryTests(tests, pageNum, pageSize);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }


    //查询我的试题
    // Todo


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

    //修改试题状态
    // Todo

    //新增试题
    // Todo
}