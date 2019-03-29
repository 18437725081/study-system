package com.bs.controller;

import com.bs.common.Constant;
import com.bs.common.ResponseCode;
import com.bs.common.ServerResponse;
import com.bs.pojo.Manager;
import com.bs.pojo.Notice;
import com.bs.service.NoticeService;
import com.bs.util.CookieUtil;
import com.bs.util.JacksonUtil;
import com.bs.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 暗香
 */
@RestController
@RequestMapping("/notice/")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("showNotice.do")
    public ServerResponse showNotice(HttpServletRequest request, Notice notice,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "100") int pageSize) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String userStr = RedisPoolUtil.get(token);
        if (StringUtils.isBlank(userStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        notice.setFlag("Y");
        return noticeService.queryNotice(notice, pageNum, pageSize);
    }

    @RequestMapping("queryNotice.do")
    public ServerResponse queryNotice(HttpServletRequest request, Notice notice,
                                      @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        //判断登录
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return noticeService.queryNotice(notice, pageNum, pageSize);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("addOrModifyNotice.do")
    public ServerResponse addOrModifyNotice(HttpServletRequest request, Notice notice) {
        //判断登录
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return noticeService.addOrModifyNotice(notice, manager);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("getNotice.do")
    public ServerResponse getNotice(HttpServletRequest request, Integer pkNotice) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return noticeService.getNotice(pkNotice);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }
}
