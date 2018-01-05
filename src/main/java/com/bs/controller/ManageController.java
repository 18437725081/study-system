package com.bs.controller;

import com.bs.common.Constant;
import com.bs.common.ResponseCode;
import com.bs.common.ServerResponse;
import com.bs.pojo.Manager;
import com.bs.service.ManageService;
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
public class ManageController {

    @Autowired
    private ManageService manageService;

    private static final Logger logger = LoggerFactory.getLogger(ManageController.class);

    /**
     * @author 张靖烽
     * @description 获取教师信息
     * @createtime 2018-01-05 10:12
     */
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
            return manageService.getTeacherList();
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    /**
     * @author 张靖烽
     * @description 新增或修改教师信息
     * @createtime 2018-01-05 10:12
     */

    /**
     * @author 张靖烽
     * @description 批量新增教师信息
     * @createtime 2018-01-05 10:12
     */

    /**
     * @author 张靖烽
     * @description 获取单条教师信息
     * @createtime 2018-01-05 10:12
     */

    /**
     * @author 张靖烽
     * @description 删除教师
     * @createtime 2018-01-05 10:12
     */

    /**
     * @author 张靖烽
     * @description 查看教师关联班级
     * @createtime 2018-01-05 10:12
     */

    /**
     * @author 张靖烽
     * @description 新增或修改教师关联班级
     * @createtime 2018-01-05 10:12
     */

    /**
     * @author 张靖烽
     * @description 删除教师关联班级
     * @createtime 2018-01-05 10:12
     */

    /**
     * @author 张靖烽
     * @description 获取学生信息
     * @createtime 2018-01-05 10:18
     */

    /**
     * @author 张靖烽
     * @description 新增或修改学生信息
     * @createtime 2018-01-05 10:12
     */

    /**
     * @author 张靖烽
     * @description 批量新增学生信息
     * @createtime 2018-01-05 10:18
     */

    /**
     * @author 张靖烽
     * @description 获取单条学生信息
     * @createtime 2018-01-05 10:18
     */

    /**
     * @author 张靖烽
     * @description 删除学生
     * @createtime 2018-01-05 10:18
     */

    /**
     * @author 张靖烽
     * @description 查看年级专业信息
     * @createtime 2018-01-05 10:27
     */

    /**
     * @author 张靖烽
     * @description 查看单条专业信息
     * @createtime 2018-01-05 10:27
     */

    /**
     * @author 张靖烽
     * @description 新增或修改年级专业信息
     * @createtime 2018-01-05 10:27
     */

    /**
     * @author 张靖烽
     * @description 删除年级专业信息
     * @createtime 2018-01-05 10:27
     */

}
