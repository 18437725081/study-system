package com.bs.controller.filter;

import com.bs.util.CookieUtil;
import com.bs.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 张靖烽
 * @name SessionExpireFilter
 * @description
 * @create 2018-07-30 17:24
 **/
public class SessionExpireFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest res = (HttpServletRequest) request;
        String token = CookieUtil.readCookie(res);
        if (StringUtils.isNotEmpty(token)) {
            String userStr = RedisPoolUtil.get(token);
            if (StringUtils.isNotBlank(userStr)) {
                RedisPoolUtil.expire(token, 60 * 30);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
