package com.bs.controller;

import com.bs.common.Constant;
import com.bs.common.ResponseCode;
import com.bs.common.ServerResponse;
import com.bs.pojo.Student;
import com.bs.service.StudentService;
import com.bs.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author 张靖烽
 * @name StudentController
 * @description 学生Controller
 * @create 2018-01-12 14:49
 **/
@Controller
@RequestMapping("/student/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * @author 张靖烽
     * @description 用户登录
     * @createtime 2017-12-27 12:45
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse login(String username, String password, HttpSession session) {
        //验证用户登录信息是否正确
        ServerResponse response = studentService.login(username, password);
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
        Student student = (Student) session.getAttribute(Constant.CURRENT_USER);
        if (student != null) {
            StudentVO studentVO = studentService.setStudentVO(student);
            return ServerResponse.createBySuccess(studentVO);
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
    }

    /**
     * @author 张靖烽
     * @description 未登录：忘记密码，获取问题
     * @createtime 2018-01-12 12:48
     */
    @RequestMapping(value = "forgetGetQuestion.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username) {
        return studentService.selectQuestion(username);
    }

    /**
     * @author 张靖烽
     * @description 未登录：忘记密码，检查答案是否正确
     * @createtime 2018-01-12 12:48
     */
    @RequestMapping(value = "forgetCheckAnswer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        return studentService.checkAnswer(username, question, answer);
    }

    /**
     * @author 张靖烽
     * @description 未登录：忘记密码，重置密码
     * @createtime 2018-01-12 12:48
     */
    @RequestMapping(value = "forgetResetPassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        return studentService.forgetResetPassword(username, passwordNew, forgetToken);
    }

    /**
     * @author 张靖烽
     * @description 已登录，重置密码
     * @createtime 2018-01-12 12:49
     */
    @RequestMapping(value = "resetPassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew) {
        Student student = (Student) session.getAttribute(Constant.CURRENT_USER);
        if (student == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        return studentService.resetStudentPassword(passwordNew, passwordOld, student);
    }

    /**
     * @author 张靖烽
     * @description 设置或更新找回密码问题和答案
     * @createtime 2018-01-12 12:49
     */
    @RequestMapping(value = "updateStudentInformation.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateStudentInformation(HttpSession session, String question, String answer) {
        Student student = (Student) session.getAttribute(Constant.CURRENT_USER);
        if (student == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        return studentService.updateStudentInformation(question, answer, student);
    }

    /**
     * @author 张靖烽
     * @description 查询待完成的试卷
     * @createtime 2018-01-12 12:49
     */
    @RequestMapping(value = "getUnfinishedPaper.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getUnfinishedPaper(HttpSession session) {
        Student student = (Student) session.getAttribute(Constant.CURRENT_USER);
        if (student == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        return studentService.getUnfinishedPaper(student);
    }

    /**
     * @author 张靖烽
     * @description 获取试卷内容
     * @createtime 2018-03-29 19:36
     */
    @RequestMapping("getPaperDetail.do")
    @ResponseBody
    public ServerResponse getPaperDetail(HttpSession session, Integer pkPaper) {
        //判断登录
        Student student = (Student) session.getAttribute(Constant.CURRENT_USER);
        if (student == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        return studentService.getPaperDetail(pkPaper, student);
    }


    /**
     * @author 张靖烽
     * @description 学生交卷，计算分数
     * @createtime 2018-03-29 20:23
     */
    @RequestMapping("submitPaper.do")
    @ResponseBody
    public ServerResponse submitPaper(HttpSession session, Integer pkPaper, String testsAndAnswer) {
        //判断登录
        Student student = (Student) session.getAttribute(Constant.CURRENT_USER);
        if (student == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        return studentService.submitPaper(pkPaper, student, testsAndAnswer);
    }
}
