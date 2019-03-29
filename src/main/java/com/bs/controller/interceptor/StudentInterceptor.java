package com.bs.controller.interceptor;

import com.bs.pojo.Student;
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
 * 学生模块拦截器
 *
 * @author 暗香
 */
public class StudentInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(StudentInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String methodName = handlerMethod.getMethod().getName();
        String className = handlerMethod.getBean().getClass().getName();
        log.info("拦截类名：{}，方法名：{}", className, methodName);
        Student student = null;
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isNotEmpty(token)) {
            String studentStr = RedisPoolUtil.get(token);
            student = JacksonUtil.stringToObj(studentStr, Student.class);
        }
        return student != null && (StringUtils.equals(student.getRole(), "2")) || BaseInterceptor.check(response, student);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
