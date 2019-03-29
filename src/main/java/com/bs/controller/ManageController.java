package com.bs.controller;

import com.bs.common.Constant;
import com.bs.common.ResponseCode;
import com.bs.common.ServerResponse;
import com.bs.pojo.Major;
import com.bs.pojo.Manager;
import com.bs.pojo.Student;
import com.bs.pojo.Teacher;
import com.bs.service.ManageService;
import com.bs.util.CookieUtil;
import com.bs.util.JacksonUtil;
import com.bs.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 暗香
 * 管理员模块
 */
@Controller
@RequestMapping("/manage/")
public class ManageController {

    @Autowired
    private ManageService manageService;

    private static final Logger log = LoggerFactory.getLogger(ManageController.class);

    /**
     * 登录
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
        ServerResponse sr = manageService.login(username, password);
        if (sr.isSuccess()) {
            CookieUtil.writeCookie(response, session.getId());
            RedisPoolUtil.setEx(session.getId(), JacksonUtil.objToString(sr.getData()), 60 * 30);
        }
        return sr;
    }

    /**
     * 获取教师信息
     *
     * @param request
     * @param teacher
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("queryTeacher.do")
    @ResponseBody
    public ServerResponse queryTeacher(HttpServletRequest request, Teacher teacher,
                                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return manageService.queryTeacher(teacher, pageNum, pageSize);
    }

    @RequestMapping("addOrUpdateTeacher.do")
    @ResponseBody
    public ServerResponse addOrUpdateTeacher(HttpServletRequest request, Teacher teacher) {
        //判断登录
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageService.addOrModifyTeacher(teacher, manager);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("getTeacherInfo.do")
    @ResponseBody
    public ServerResponse getTeacherInfo(HttpServletRequest request, Integer pkTeacher) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageService.getTeacherInfo(pkTeacher);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("delTeacher.do")
    @ResponseBody
    public ServerResponse delTeacher(HttpServletRequest request, Integer pkTeacher) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageService.delTeacher(pkTeacher);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("queryStudent.do")
    @ResponseBody
    public ServerResponse queryStudent(HttpServletRequest request, Student student,
                                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageService.queryStudent(student, pageNum, pageSize);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("addOrUpdateStudent.do")
    @ResponseBody
    public ServerResponse addOrUpdateStudent(HttpServletRequest request, Student student) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageService.addOrModifyStudent(student, manager);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("getStudentInfo.do")
    @ResponseBody
    public ServerResponse getStudentInfo(HttpServletRequest request, Integer pkStudent) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageService.getStudentInfo(pkStudent);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("delStudent.do")
    @ResponseBody
    public ServerResponse delStudent(HttpServletRequest request, Integer pkStudent) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageService.delStudent(pkStudent);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("getRelTeacherMajor.do")
    @ResponseBody
    public ServerResponse getRelTeacherMajor(HttpServletRequest request, Integer pkTeacher) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageService.getRelTeacherMajor(pkTeacher);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("addRelTeacherMajor.do")
    @ResponseBody
    public ServerResponse addRelTeacherMajor(HttpServletRequest request, Integer pkTeacher, Integer pkMajor) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageService.addRelTeacherMajor(pkTeacher, pkMajor, manager);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("delRelTeacherMajor.do")
    @ResponseBody
    public ServerResponse delRelTeacherMajor(HttpServletRequest request, Integer pkTeacher, Integer pkMajor) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            log.info(manager.getPkManager() + "执行了删除教师：" + pkTeacher + " 关联的：" + pkMajor + " 专业的操作");
            return manageService.delRelTeacherMajor(pkTeacher, pkMajor);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("getGrade.do")
    @ResponseBody
    public ServerResponse getGrade(HttpServletRequest request) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageService.getGrade();
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("getMajor.do")
    @ResponseBody
    public ServerResponse getMajor(HttpServletRequest request, String grade) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageService.getMajor(grade);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("getTeacherMajor.do")
    @ResponseBody
    public ServerResponse getTeacherMajor(HttpServletRequest request, Integer pkTeacher,
                                          @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageService.getTeacherMajor(pkTeacher, pageNum, pageSize);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("queryMajor.do")
    @ResponseBody
    public ServerResponse queryMajor(HttpServletRequest request, Major major,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageService.queryMajor(major, pageNum, pageSize);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("getMajorInfo.do")
    @ResponseBody
    public ServerResponse getMajorInfo(HttpServletRequest request, Integer pkMajor) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageService.getMajorInfo(pkMajor);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("addOrUpdateMajor.do")
    @ResponseBody
    public ServerResponse addOrUpdateMajor(HttpServletRequest request, Major major) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageService.addOrModifyMajor(major, manager);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("delMajor.do")
    @ResponseBody
    public ServerResponse delMajor(HttpServletRequest request, Integer pkMajor) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageService.delMajor(pkMajor);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("resetTeacherPwd.do")
    @ResponseBody
    public ServerResponse resetTeacherPwd(HttpServletRequest request, Integer pkTeacher) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageService.resetTeacherPwd(pkTeacher);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }

    @RequestMapping("resetStudentPwd.do")
    @ResponseBody
    public ServerResponse resetStudentPwd(HttpServletRequest request, Integer pkStudent) {
        String token = CookieUtil.readCookie(request);
        if (StringUtils.isEmpty(token)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        String manageStr = RedisPoolUtil.get(token);
        Manager manager = JacksonUtil.stringToObj(manageStr, Manager.class);
        if (manager == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        if (Constant.Role.ROLE_ADMIN.equals(manager.getRole())) {
            return manageService.resetStudentPwd(pkStudent);
        }
        return ServerResponse.createByErrorMessage("不是管理员，无法操作");
    }
}
