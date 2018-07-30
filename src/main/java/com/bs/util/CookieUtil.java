package com.bs.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 张靖烽
 * @name CookieUtil
 * @description
 * @create 2018-07-10 11:11
 **/
public class CookieUtil {

    //cookie保存在.qx.com域名下
    private final static String COOKIE_DOMAIN = ".qx.com";
    //cookie名称
    private final static String COOKIE_NAME = "qx";

    /**
     * @author 张靖烽
     * @description 存入Cookie
     * @createtime 2018-07-30 15:22
     */
    public static void writeCookie(HttpServletResponse response, String value) {
        Cookie cookie = new Cookie(COOKIE_NAME, value);
        cookie.setDomain(COOKIE_DOMAIN);
        cookie.setPath("/");
        //防止脚本获取cookie信息
        cookie.setHttpOnly(true);
        //cookie有效期
        cookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(cookie);
    }

    /**
     * @author 张靖烽
     * @description 读取Cookie
     * @createtime 2018-07-30 15:22
     */
    public static String readCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie ck : cookies){
                if (StringUtils.equals(ck.getName(),COOKIE_NAME)){
                    return ck.getValue();
                }
            }
        }
        return null;
    }

    /**
     * @author 张靖烽
     * @description 删除Cookie
     * @createtime 2018-07-30 15:22
     */
    public static void deleteCookie(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie ck : cookies){
                if (StringUtils.equals(ck.getName(),COOKIE_NAME)){
                    ck.setDomain(COOKIE_DOMAIN);
                    ck.setPath("/");
                    ck.setHttpOnly(true);
                    //直接设置Cookie有效期为0，表示删除此Cookie
                    ck.setMaxAge(0);
                    response.addCookie(ck);
                }
            }
        }
    }
}
