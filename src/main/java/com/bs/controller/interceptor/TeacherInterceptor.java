package com.bs.controller.interceptor;

import com.bs.common.ResponseCode;
import com.bs.common.ServerResponse;
import com.bs.pojo.Teacher;
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
import java.io.PrintWriter;

/**
 * @author 张靖烽
 * @name TeacherInterceptor
 * @description
 * @create 2018-07-31 0:01
 **/
public class TeacherInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TeacherInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String methodName = handlerMethod.getMethod().getName();
        String className = handlerMethod.getBean().getClass().getName();
        log.info("拦截类名：{}，方法名：{}", className, methodName);
        Teacher teacher = null;
        //从redis获取用户信息
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isNotEmpty(token)) {
            String studentStr = RedisPoolUtil.get(token);
            teacher = JacksonUtil.stringToObj(studentStr, Teacher.class);
        }
        //用户信息为空或者身份权限不对
        if (teacher == null || (!StringUtils.equals(teacher.getRole(), "1"))) {
            //重置response
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            if (teacher == null) {
                printWriter.print(JacksonUtil.objToString(ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "操作被拦截，请先登录")));
            } else {
                printWriter.print(JacksonUtil.objToString(ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "无该操作权限")));
            }
            printWriter.flush();
            printWriter.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}