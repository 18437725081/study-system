package com.bs.controller;

import com.bs.common.Constant;
import com.bs.common.ServerResponse;
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
 * @description
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

}
