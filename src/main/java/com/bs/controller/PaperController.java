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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 张靖烽
 * @name PaperController
 * @description
 * @create 2018-03-14 15:48
 **/
@Controller
@RequestMapping("/paper/")
public class PaperController {

    @Autowired
    private PaperService paperService;

    /**
     * @author 张靖烽
     * @description 查询试卷
     * @createtime 2018-03-14 15:53
     */
    @RequestMapping("queryPaper.do")
    @ResponseBody
    public ServerResponse queryPaper(HttpServletRequest request, Paper paper,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
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
            paper.setFlagPublic("Y");
            return paperService.queryPaper(paper, pageNum, pageSize);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    /**
     * @author 张靖烽
     * @description 查询我的试卷
     * @createtime 2018-03-14 17:00
     */
    @RequestMapping("queryMyPaper.do")
    @ResponseBody
    public ServerResponse queryMyPaper(HttpServletRequest request, Paper paper,
                                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
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
            paper.setCreatedBy(teacher.getPkTeacher());
            return paperService.queryPaper(paper, pageNum, pageSize);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    /**
     * @author 张靖烽
     * @description 新增试卷
     * @createtime 2018-03-14 19:18
     */
    @RequestMapping("addPaper.do")
    @ResponseBody
    public ServerResponse addPaper(HttpServletRequest request, String paperName, String publicFlag) {
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
            return paperService.addPaper(paperName, publicFlag, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    /**
     * @author 张靖烽
     * @description 修改公开状态
     * @createtime 2018-03-14 18:51
     */
    @RequestMapping("modifyPublicFlag.do")
    @ResponseBody
    public ServerResponse modifyPublicFlag(HttpServletRequest request, Integer pkPaper, String flag) {
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
            return paperService.modifyPublicFlag(pkPaper, flag, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    /**
     * @author 张靖烽
     * @description 修改有效状态
     * @createtime 2018-03-14 18:51
     */
    @RequestMapping("modifyFlag.do")
    @ResponseBody
    public ServerResponse modifyFlag(HttpServletRequest request, Integer pkPaper, String flag) {
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
            return paperService.modifyFlag(pkPaper, flag, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    /**
     * @author 张靖烽
     * @description 试卷预览
     * @createtime 2018-03-14 20:15
     */
    @RequestMapping("paperDetail.do")
    @ResponseBody
    public ServerResponse paperDetail(HttpServletRequest request, Integer pkPaper) {
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
            return paperService.paperDetail(pkPaper, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    /**
     * @author 张靖烽
     * @description 手动组卷, 添加试题
     * @createtime 2018-03-15 11:24
     */
    @RequestMapping("compositionPaper.do")
    @ResponseBody
    public ServerResponse compositionPaper(HttpServletRequest request, PaperDetail paperDetail) {
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
            return paperService.compositionPaper(paperDetail, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    /**
     * @author 张靖烽
     * @description 删除试卷试题
     * @createtime 2018-03-15 19:09
     */
    @RequestMapping("deleteTestsFromPaper.do")
    @ResponseBody
    public ServerResponse deleteTestsFromPaper(HttpServletRequest request, Integer fkTest, Integer fkPaper) {
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
            return paperService.deleteTestsFromPaper(fkTest, fkPaper, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    /**
     * @author 张靖烽
     * @description 清空试卷试题
     * @createtime 2018-03-15 19:09
     */
    @RequestMapping("emptyTestsFromPaper.do")
    @ResponseBody
    public ServerResponse emptyTestsFromPaper(HttpServletRequest request, Integer fkPaper) {
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
            return paperService.emptyTestsFromPaper(fkPaper, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

    /**
     * @author 张靖烽
     * @description 发布试卷
     * @createtime 2018-03-15 20:04
     */
    @RequestMapping("assignmentPaper.do")
    @ResponseBody
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

    /**
     * @param paperName    试卷名称
     * @param subject      题目所属学科
     * @param flagPublic   是否公开
     * @param optionNumber 选择题数量
     * @param optionScore  每道选择题分数
     * @author 张靖烽
     * @description 自动组卷
     * @createtime 2018-03-15 20:25
     */
    @RequestMapping("autoBuildPaper.do")
    @ResponseBody
    public ServerResponse autoBuildPaper(HttpServletRequest request,
                                         String paperName,
                                         String subject,
                                         @RequestParam(value = "flagPublic", defaultValue = "N") String flagPublic,
                                         @RequestParam(value = "optionNumber", defaultValue = "20") Integer optionNumber,
                                         @RequestParam(value = "optionScore", defaultValue = "5") String optionScore
    ) {
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
            return paperService.autoBuildPaper(paperName, flagPublic, optionNumber, optionScore, subject, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }


    /**
     * @author 张靖烽
     * @description 查询试卷试题
     * @createtime 2018-03-08 13:41
     */
    @RequestMapping("selectPaperTests.do")
    @ResponseBody
    public ServerResponse selectPaperTests(HttpServletRequest request,Integer fkPaper) {
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
            return paperService.selectPaperTests(fkPaper);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }

}
