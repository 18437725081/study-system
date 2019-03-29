package com.bs.controller;

import com.bs.common.ResponseCode;
import com.bs.common.ServerResponse;
import com.bs.pojo.Student;
import com.bs.service.StudentService;
import com.bs.util.CookieUtil;
import com.bs.util.JacksonUtil;
import com.bs.util.RedisPoolUtil;
import com.bs.vo.StudentVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * 学生
 *
 * @author 暗香
 */
@Controller
@RequestMapping("/student/")
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse login1(String username, String password, HttpSession session, HttpServletResponse response) {
        ServerResponse sr = studentService.login(username, password);
        if (sr.isSuccess()) {
            CookieUtil.writeCookie(response, session.getId());
            RedisPoolUtil.setEx(session.getId(), JacksonUtil.objToString(sr.getData()), 60 * 30);
        }
        return sr;
    }

    @RequestMapping(value = "getUserName.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getUserName(HttpServletRequest request) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String studentStr = RedisPoolUtil.get(token);
        Student student = JacksonUtil.stringToObj(studentStr, Student.class);
        if (student != null) {
            StudentVO studentVO = studentService.setStudentVO(student);
            return ServerResponse.createBySuccess(studentVO);
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
    }

    @RequestMapping(value = "forgetGetQuestion.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username) {
        return studentService.selectQuestion(username);
    }

    @RequestMapping(value = "forgetCheckAnswer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        return studentService.checkAnswer(username, question, answer);
    }

    @RequestMapping(value = "forgetResetPassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        return studentService.forgetResetPassword(username, passwordNew, forgetToken);
    }

    @RequestMapping(value = "resetPassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpServletRequest request, String passwordOld, String passwordNew) {
        String studentStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(studentStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Student student = JacksonUtil.stringToObj(studentStr, Student.class);
        if (student == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        return studentService.resetStudentPassword(passwordNew, passwordOld, student);
    }

    @RequestMapping(value = "updateStudentInformation.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateStudentInformation(HttpServletRequest request, String question, String answer) {
        String studentStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(studentStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Student student = JacksonUtil.stringToObj(studentStr, Student.class);
        if (student == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        return studentService.updateStudentInformation(question, answer, student);
    }

    @RequestMapping(value = "getUnfinishedPaper.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getUnfinishedPaper(HttpServletRequest request) {
        String studentStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(studentStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Student student = JacksonUtil.stringToObj(studentStr, Student.class);
        if (student == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        return studentService.getUnfinishedPaper(student);
    }

    @RequestMapping("getPaperDetail.do")
    @ResponseBody
    public ServerResponse getPaperDetail(HttpServletRequest request, Integer pkPaper) {
        String studentStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(studentStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Student student = JacksonUtil.stringToObj(studentStr, Student.class);
        if (student == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        return studentService.getPaperDetail(pkPaper, student);
    }


    @RequestMapping("submitPaper.do")
    @ResponseBody
    public ServerResponse submitPaper(HttpServletRequest request, Integer pkPaper, String testsAndAnswer) {
        String studentStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(studentStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Student student = JacksonUtil.stringToObj(studentStr, Student.class);
        if (student == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        return studentService.submitPaper(pkPaper, student, testsAndAnswer);
    }

    @RequestMapping("inquiryScore.do")
    @ResponseBody
    public ServerResponse inquiryScore(HttpServletRequest request) {
        try {
            String studentStr = checkLoginStatus(request);
            if (StringUtils.isEmpty(studentStr)) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
            }
            Student student = JacksonUtil.stringToObj(studentStr, Student.class);
            if (student == null) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
            }
            return studentService.inquiryScore(student);
        } catch (Exception e) {
            log.error("学生查询成绩:" + e);
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录或无权限");
        }
    }
}
