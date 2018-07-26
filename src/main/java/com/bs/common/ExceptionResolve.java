package com.bs.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 张靖烽
 * @name ExceptionResolve
 * @description 全局异常处理类
 * @create 2018-07-26 9:58
 **/
@Component
public class ExceptionResolve implements HandlerExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(ExceptionResolve.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        log.error("请求：{} 发生异常！", request.getRequestURI(), e);
        // 如果jackson用的是2.0+版本，这里使用MappingJackson2JsonView
        ModelAndView modelAndView = new ModelAndView(new MappingJacksonJsonView());
        modelAndView.addObject("status",ResponseCode.ERROR.getCode());
        modelAndView.addObject("msg","系统异常，详情请联系管理员");
        return modelAndView;
    }
}
