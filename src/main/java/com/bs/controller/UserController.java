package com.bs.controller;

import com.bs.common.ServerResponse;
import com.bs.util.CookieUtil;
import com.bs.util.RedisPoolUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户相关操作
 *
 * @author 暗香
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    /**
     * 登出操作
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "loginOut.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> loginout(HttpServletRequest request, HttpServletResponse response) {
        //将用户信息清除
        String token = CookieUtil.readCookie(request);
        CookieUtil.deleteCookie(request, response);
        RedisPoolUtil.del(token);
        return ServerResponse.createBySuccess();
    }
}
