package com.bs.controller;

import com.bs.common.CheckUtil;
import com.bs.common.ResponseCode;
import com.bs.common.ServerResponse;
import com.bs.pojo.Teacher;
import com.bs.service.TeacherService;
import com.bs.util.CookieUtil;
import com.bs.util.JacksonUtil;
import com.bs.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.bs.common.CheckUtil.checkLoginStatus;

/**
 * @author 教师
 */
@Controller
@RequestMapping("/teacher/")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param session
     * @param response
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse login(String username, String password, HttpSession session, HttpServletResponse response) {
        ServerResponse sr = teacherService.login(username, password);
        if (sr.isSuccess()) {
            CookieUtil.writeCookie(response, session.getId());
            RedisPoolUtil.setEx(session.getId(), JacksonUtil.objToString(sr.getData()), 60 * 30);
        }
        return sr;
    }

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "getUserName.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getUserName(HttpServletRequest request) {
        String teacherStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(teacherStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
        if (teacher != null) {
            return ServerResponse.createBySuccess(teacher);
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
    }

    /**
     * 找回密码问题
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "forgetGetQuestion.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username) {
        return teacherService.selectQuestion(username);
    }

    /**
     * 未登录：忘记密码，检查答案是否正确
     *
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @RequestMapping(value = "forgetCheckAnswer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        return teacherService.checkAnswer(username, question, answer);
    }

    /**
     * 未登录：忘记密码，重置密码
     *
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    @RequestMapping(value = "forgetResetPassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        return teacherService.forgetResetPassword(username, passwordNew, forgetToken);
    }

    /**
     * 已登录，重置密码
     *
     * @param request
     * @param passwordOld
     * @param passwordNew
     * @return
     */
    @RequestMapping(value = "resetPassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpServletRequest request, String passwordOld, String passwordNew) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String teacherStr = RedisPoolUtil.get(token);
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        return teacherService.resetTeacherPassword(passwordNew, passwordOld, teacher);
    }

    /**
     * 设置或更新找回密码问题和答案
     *
     * @param request
     * @param question
     * @param answer
     * @return
     */
    @RequestMapping(value = "updateTeacherInformation.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateTeacherInformation(HttpServletRequest request, String question, String answer) {
        String teacherStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(teacherStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        return teacherService.updateTeacherInformation(question, answer, teacher);
    }


    /**
     * 获取教师管理的专业信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "getTeacherMajor.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getTeacherMajor(HttpServletRequest request) {
        String teacherStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(teacherStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        return teacherService.getTeacherMajor(teacher);
    }
}
