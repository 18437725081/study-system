package com.bs.controller;

import com.bs.common.Constant;
import com.bs.common.ResponseCode;
import com.bs.common.ServerResponse;
import com.bs.pojo.Manager;
import com.bs.pojo.Student;
import com.bs.pojo.Teacher;
import com.bs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author 张靖烽
 * @name UserController
 * @description 用户相关操作Controller
 * @create 2017-12-26 11:06
 **/
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @author 张靖烽
     * @description 用户登录
     * @createtime 2017-12-27 12:45
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse login(String username, String password, String role, HttpSession session) {
        //验证用户登录信息是否正确
        ServerResponse response = userService.login(username, password, role);
        //验证通过，将当前用户信息放入session
        if (response.isSuccess()) {
            session.setAttribute(Constant.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * @author 张靖烽
     * @description 用户登出
     * @createtime 2017-12-27 12:45
     */
    @RequestMapping(value = "loginOut.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> loginout(HttpSession session) {
        //将用户信息从session中清除
        session.removeAttribute(Constant.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * @author 张靖烽
     * @description 获取用户名字
     * @createtime 2018-01-09 14:02
     */
    @RequestMapping(value = "getUserName.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getUserName(HttpSession session) {
        //判断用户是否登录
        if (session.getAttribute(Constant.CURRENT_USER) == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //根据用户类别进行返回
        if (session.getAttribute(Constant.CURRENT_USER).getClass() == Manager.class) {
            Manager manager = (Manager) session.getAttribute(Constant.CURRENT_USER);
            return ServerResponse.createBySuccess("管理员");
        }
        if (session.getAttribute(Constant.CURRENT_USER).getClass() == Teacher.class) {
            Teacher teacher = (Teacher) session.getAttribute(Constant.CURRENT_USER);
            return ServerResponse.createBySuccess(teacher.getName());
        }
        if (session.getAttribute(Constant.CURRENT_USER).getClass() == Student.class) {
            Student student = (Student) session.getAttribute(Constant.CURRENT_USER);
            return ServerResponse.createBySuccess(student.getName());
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "非法人员");
    }
}
