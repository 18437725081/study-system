package com.bs.controller;

import com.bs.common.Constant;
import com.bs.common.ResponseCode;
import com.bs.common.ServerResponse;
import com.bs.pojo.Paper;
import com.bs.pojo.PaperDetail;
import com.bs.pojo.Teacher;
import com.bs.service.PaperService;
import com.bs.util.CookieUtil;
import com.bs.util.JacksonUtil;
import com.bs.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.bs.common.CheckUtil.checkLoginStatus;

/**
 * @author 暗香
 */
@RestController
@RequestMapping("/paper/")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @RequestMapping("queryPaper.do")
    public ServerResponse queryPaper(HttpServletRequest request, Paper paper,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        String teacherStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(teacherStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            paper.setFlagPublic("Y");
            return paperService.queryPaper(paper, pageNum, pageSize);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    @RequestMapping("queryMyPaper.do")
    public ServerResponse queryMyPaper(HttpServletRequest request, Paper paper,
                                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        String teacherStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(teacherStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            paper.setCreatedBy(teacher.getPkTeacher());
            return paperService.queryPaper(paper, pageNum, pageSize);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    @RequestMapping("addPaper.do")
    public ServerResponse addPaper(HttpServletRequest request, String paperName, String publicFlag) {
        String teacherStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(teacherStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            return paperService.addPaper(paperName, publicFlag, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    @RequestMapping("modifyPublicFlag.do")
    public ServerResponse modifyPublicFlag(HttpServletRequest request, Integer pkPaper, String flag) {
        String teacherStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(teacherStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            return paperService.modifyPublicFlag(pkPaper, flag, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    @RequestMapping("modifyFlag.do")
    public ServerResponse modifyFlag(HttpServletRequest request, Integer pkPaper, String flag) {
        String teacherStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(teacherStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            return paperService.modifyFlag(pkPaper, flag, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    @RequestMapping("paperDetail.do")
    public ServerResponse paperDetail(HttpServletRequest request, Integer pkPaper) {
        String teacherStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(teacherStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            return paperService.paperDetail(pkPaper, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    @RequestMapping("compositionPaper.do")
    public ServerResponse compositionPaper(HttpServletRequest request, PaperDetail paperDetail) {
        String teacherStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(teacherStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            return paperService.compositionPaper(paperDetail, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    @RequestMapping("deleteTestsFromPaper.do")
    public ServerResponse deleteTestsFromPaper(HttpServletRequest request, Integer fkTest, Integer fkPaper) {
        String teacherStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(teacherStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            return paperService.deleteTestsFromPaper(fkTest, fkPaper, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    @RequestMapping("emptyTestsFromPaper.do")
    public ServerResponse emptyTestsFromPaper(HttpServletRequest request, Integer fkPaper) {
        String teacherStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(teacherStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            return paperService.emptyTestsFromPaper(fkPaper, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    @RequestMapping("assignmentPaper.do")
    public ServerResponse assignmentPaper(HttpServletRequest request, Integer fkPaper, Integer fkMajor) {
        //判断登录
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String teacherStr = RedisPoolUtil.get(token);
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            return paperService.assignmentPaper(fkPaper, fkMajor, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    @RequestMapping("autoBuildPaper.do")
    public ServerResponse autoBuildPaper(HttpServletRequest request,
                                         String paperName,
                                         String subject,
                                         @RequestParam(value = "flagPublic", defaultValue = "N") String flagPublic,
                                         @RequestParam(value = "optionNumber", defaultValue = "20") Integer optionNumber,
                                         @RequestParam(value = "optionScore", defaultValue = "5") String optionScore) {

        String teacherStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(teacherStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            return paperService.autoBuildPaper(paperName, flagPublic, optionNumber, optionScore, subject, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }


    @RequestMapping("selectPaperTests.do")
    public ServerResponse selectPaperTests(HttpServletRequest request, Integer fkPaper) {
        String teacherStr = checkLoginStatus(request);
        if (StringUtils.isEmpty(teacherStr)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        Teacher teacher = JacksonUtil.stringToObj(teacherStr, Teacher.class);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            return paperService.selectPaperTests(fkPaper);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

}
