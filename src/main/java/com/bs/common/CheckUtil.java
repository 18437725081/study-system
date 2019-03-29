package com.bs.common;

import com.bs.util.CookieUtil;
import com.bs.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录工具类
 *
 * @author : 暗香
 */

public class CheckUtil {

    /**
     * 判断是否登录
     *
     * @param request 请求体对象
     * @return
     */
    public static String checkLoginStatus(HttpServletRequest request) {
        String token = CookieUtil.readCookie(request);
        return StringUtils.isEmpty(token) ? StringUtils.EMPTY : RedisPoolUtil.get(token);
    }
}

