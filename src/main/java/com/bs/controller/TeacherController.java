package com.bs.controller;

import com.bs.common.Constant;
import com.bs.common.ResponseCode;
import com.bs.common.ServerResponse;
import com.bs.pojo.Teacher;
import com.bs.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author 张靖烽
 * @name TeacherController
 * @description 教师Controller
 * @create 2018-01-12 14:49
 **/
@Controller
@RequestMapping("/teacher/")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * @author 张靖烽
     * @description 用户登录
     * @createtime 2017-12-27 12:45
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse login(String username, String password , HttpSession session) {
        //验证用户登录信息是否正确
        ServerResponse response = teacherService.login(username, password);
        //验证通过，将当前用户信息放入session
        if (response.isSuccess()) {
            session.setAttribute(Constant.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * @author 张靖烽
     * @description 获取用户信息
     * @createtime 2018-01-09 14:02
     */
    @RequestMapping(value = "getUserName.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getUserName(HttpSession session) {
        Teacher teacher = (Teacher)session.getAttribute(Constant.CURRENT_USER);
        if (teacher != null){
            return ServerResponse.createBySuccess(teacher);
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请先登录");
    }

    /**
     * @author 张靖烽
     * @description 未登录：忘记密码，获取问题
     * @createtime 2018-01-12 12:48
     */
    @RequestMapping(value = "forgetGetQuestion.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username) {
        return teacherService.selectQuestion(username);
    }

    /**
     * @author 张靖烽
     * @description 未登录：忘记密码，检查答案是否正确
     * @createtime 2018-01-12 12:48
     */
    @RequestMapping(value = "forgetCheckAnswer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        return teacherService.checkAnswer(username, question, answer);
    }

    /**
     * @author 张靖烽
     * @description 未登录：忘记密码，重置密码
     * @createtime 2018-01-12 12:48
     */
    @RequestMapping(value = "forgetResetPassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        return teacherService.forgetResetPassword(username, passwordNew, forgetToken);
    }

    /**
     * @author 张靖烽
     * @description 已登录，重置密码
     * @createtime 2018-01-12 12:49
     */
    @RequestMapping(value = "resetPassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew) {
        Teacher teacher = (Teacher) session.getAttribute(Constant.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        return teacherService.resetTeacherPassword(passwordNew, passwordOld, teacher);
    }

    /**
     * @author 张靖烽
     * @description 设置或更新找回密码问题和答案
     * @createtime 2018-01-12 12:49
     */
    @RequestMapping(value = "updateTeacherInformation.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateTeacherInformation(HttpSession session, String question, String answer) {
        Teacher teacher = (Teacher) session.getAttribute(Constant.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        return teacherService.updateTeacherInformation(question, answer, teacher);
    }


    /**
     * @author 张靖烽
     * @description 获取教师管理的专业信息
     * @createtime 2018-01-12 12:49
     */
    @RequestMapping(value = "getTeacherMajor.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getTeacherMajor(HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute(Constant.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        return teacherService.getTeacherMajor(teacher);
    }
}
