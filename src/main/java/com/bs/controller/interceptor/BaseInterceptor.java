package com.bs.controller.interceptor;

import com.alibaba.fastjson.JSON;
import com.bs.common.ResponseCode;
import com.bs.common.ServerResponse;
import com.bs.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * 基类拦截器
 *
 * @author 暗香
 */
class BaseInterceptor {

    private static final Logger log = LoggerFactory.getLogger(BaseInterceptor.class);

    static boolean check(HttpServletResponse response, Object object) {
        //重置response
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter;
        try {
            printWriter = response.getWriter();
            if (Objects.isNull(object)) {
                printWriter.print(JSON.toJSONString(ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "操作被拦截，请先登录")));
            } else {
                printWriter.print(JSON.toJSONString(ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "无该操作权限")));
            }
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            log.error("拦截请求是发生异常", e);
            return false;
        }
        return false;
    }
}
