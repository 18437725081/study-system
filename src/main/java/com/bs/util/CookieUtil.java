package com.bs.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 暗香
 **/
public class CookieUtil {

    private final static String COOKIE_DOMAIN = "localhost";

    private final static String COOKIE_NAME = "qx";

    /**
     * 写cookie
     *
     * @param response 响应对象
     * @param value    cookie值
     */
    public static void writeCookie(HttpServletResponse response, String value) {
        Cookie cookie = new Cookie(COOKIE_NAME, value);
        cookie.setDomain(COOKIE_DOMAIN);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(cookie);
    }

    /**
     * 取cookie的值
     *
     * @param request 请求体对象
     * @return cookie的值
     */
    public static String readCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie ck : cookies) {
                if (StringUtils.equals(ck.getName(), COOKIE_NAME)) {
                    return ck.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 清空cookie
     *
     * @param request
     * @param response
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie ck : cookies) {
                if (StringUtils.equals(ck.getName(), COOKIE_NAME)) {
                    ck.setDomain(COOKIE_DOMAIN);
                    ck.setPath("/");
                    ck.setHttpOnly(true);
                    ck.setMaxAge(0);
                    response.addCookie(ck);
                }
            }
        }
    }
}
