package com.bs.controller;

import com.bs.common.Constant;
import com.bs.common.ResponseCode;
import com.bs.common.ServerResponse;
import com.bs.pojo.Manager;
import com.bs.pojo.Notice;
import com.bs.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 张靖烽
 * @name NoticeController
 * @description 通知管理Controller
 * @create 2017-12-21 10:06
 **/
@Controller
@RequestMapping("/notice/")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    /**
     * @author 张靖烽
     * @description 获取有效通知内容，用于首页展示
     * @createtime 2017-12-29 12:44
     */
    @RequestMapping("showNotice.do")
    @ResponseBody
    public ServerResponse<List> showNotice(HttpSession session) {
        //判断用户是否登录
        if (session.getAttribute(Constant.CURRENT_USER) == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        List list = noticeService.selectNotice();
        return ServerResponse.createBySuccess(list);
    }

    /**
     * @author 张靖烽
     * @description 获取所有通知
     * @createtime 2017-12-29 12:44
     */
    @RequestMapping("manageNotice.do")
    @ResponseBody
    public ServerResponse manageNotice(HttpSession session) {
        //判断登录
        Manager manager = (Manager) session.getAttribute(Constant.CURRENT_USER);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return noticeService.manageNotice();
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    /**
     * @author 张靖烽
     * @description 查询
     * @createtime 2017-12-29 12:44
     */
    @RequestMapping("queryNotice.do")
    @ResponseBody
    public ServerResponse queryNotice(HttpSession session,Notice notice) {
        //判断登录
        Manager manager = (Manager) session.getAttribute(Constant.CURRENT_USER);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return noticeService.queryNotice(notice);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    /**
     * @author 张靖烽
     * @description 新增或更新通知
     * @createtime 2017-12-29 12:51
     */
    @RequestMapping("addOrModifyNotice.do")
    @ResponseBody
    public ServerResponse addOrModifyNotice(HttpSession session, Notice notice) {
        //判断登录
        Manager manager = (Manager) session.getAttribute(Constant.CURRENT_USER);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return noticeService.addOrModifyNotice(notice, manager);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    /**
     * @author 张靖烽
     * @description 删除通知, 预留接口
     * @createtime 2017-12-29 12:51
     */
    @RequestMapping("deleteNotice.do")
    @ResponseBody
    public ServerResponse deleteNotice(HttpSession session, Notice notice) {
        return null;
    }

    /**
     * @author 张靖烽
     * @description 获取单条通知内容
     * @createtime 2018-01-04 8:54
     */
    @RequestMapping("getNotice.do")
    @ResponseBody
    public ServerResponse getNotice(HttpSession session, Integer pkNotice) {
        //判断登录
        Manager manager = (Manager) session.getAttribute(Constant.CURRENT_USER);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return noticeService.getNotice(pkNotice);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }
}
