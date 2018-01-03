package com.bs.controller;

import com.bs.common.Constant;
import com.bs.common.ResponseCode;
import com.bs.common.ServerResponse;
import com.bs.pojo.Manager;
import com.bs.service.ManageUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author 张靖烽
 * @name ManageUserController
 * @description
 * @create 2018-01-03 20:24
 **/
@Controller
@RequestMapping("/manage/")
public class ManageUserController {

    @Autowired
    private ManageUserService manageUserService;

    private static final Logger logger = LoggerFactory.getLogger(ManageUserController.class);

    @RequestMapping("getTeacherList.do")
    @ResponseBody
    public ServerResponse getTeacherList(HttpSession session) {
        //判断登录
        Manager manager = (Manager) session.getAttribute(Constant.CURRENT_USER);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageUserService.getTeacherList();
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

}
