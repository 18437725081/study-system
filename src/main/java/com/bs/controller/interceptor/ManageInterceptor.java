package com.bs.controller.interceptor;

import com.alibaba.fastjson.JSON;
import com.bs.pojo.Manager;
import com.bs.util.CookieUtil;
import com.bs.util.JacksonUtil;
import com.bs.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 *
 * @author 暗香
 */
public class ManageInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(ManageInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String methodName = handlerMethod.getMethod().getName();
        String className = handlerMethod.getBean().getClass().getName();
        log.info("拦截类名：{}，方法名：{}", className, methodName);
        Manager manager = null;
        //从redis获取用户信息
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isNotEmpty(token)) {
            String studentStr = RedisPoolUtil.get(token);
            manager = JacksonUtil.stringToObj(studentStr, Manager.class);
        }
        //判断用户信息是否为空或者身份权限不对
        return manager != null && (StringUtils.equals(manager.getRole(), "0")) || BaseInterceptor.check(response, manager);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
