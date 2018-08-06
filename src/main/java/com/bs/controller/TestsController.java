package com.bs.controller;

import com.bs.common.Constant;
import com.bs.common.ResponseCode;
import com.bs.common.ServerResponse;
import com.bs.pojo.Teacher;
import com.bs.pojo.Tests;
import com.bs.service.TestsService;
import com.bs.util.CookieUtil;
import com.bs.util.JacksonUtil;
import com.bs.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * @author 张靖烽
     * @description 查询试题
     * @createtime 2018-03-07 19:23
     */
    @RequestMapping("queryTests.do")
    @ResponseBody
    public ServerResponse queryTests(Tests tests,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        tests.setFlag("Y");
        return testsService.queryTests(tests, pageNum, pageSize);
    }


    /**
     * @author 张靖烽
     * @description 查询我的试题
     * @createtime 2018-03-08 12:39
     */
    @RequestMapping("queryMyTests.do")
    @ResponseBody
    public ServerResponse queryMyTests(HttpServletRequest request, Tests tests,
                                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        //判断登录
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String teacherStr = RedisPoolUtil.get(token);
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
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
    public ServerResponse getTestsInfo(HttpServletRequest request, Integer pkTest) {
        //判断登录
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String teacherStr = RedisPoolUtil.get(token);
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
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
    public ServerResponse addTest(HttpServletRequest request, Tests tests) {
        //判断登录
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String teacherStr = RedisPoolUtil.get(token);
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
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
    public ServerResponse modifyTestFlag(HttpServletRequest request, Integer pkTest, String flag) {
        //判断登录
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String teacherStr = RedisPoolUtil.get(token);
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
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
    public ServerResponse selectSubjectList(HttpServletRequest request) {
        //判断登录
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String teacherStr = RedisPoolUtil.get(token);
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            return testsService.selectSubjectList();
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }
}
