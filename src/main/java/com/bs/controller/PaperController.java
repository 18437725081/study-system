package com.bs.controller;

import com.bs.common.Constant;
import com.bs.common.ResponseCode;
import com.bs.common.ServerResponse;
import com.bs.pojo.Paper;
import com.bs.pojo.Teacher;
import com.bs.service.PaperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    private static final Logger log = LoggerFactory.getLogger(PaperController.class);


    /**
     * @author 张靖烽
     * @description 查询试卷
     * @createtime 2018-03-14 15:53
     */
    @RequestMapping("queryPaper.do")
    @ResponseBody
    public ServerResponse queryPaper(HttpSession session, Paper paper,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        //判断登录
        Teacher teacher = (Teacher) session.getAttribute(Constant.CURRENT_USER);
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
    public ServerResponse queryMyPaper(HttpSession session, Paper paper,
                                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        //判断登录
        Teacher teacher = (Teacher) session.getAttribute(Constant.CURRENT_USER);
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
    public ServerResponse addPaper(HttpSession session, String paperName, String publicFlag) {
        //判断登录
        Teacher teacher = (Teacher) session.getAttribute(Constant.CURRENT_USER);
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
    public ServerResponse modifyPublicFlag(HttpSession session, Integer pkPaper, String publicFlag) {
        //判断登录
        Teacher teacher = (Teacher) session.getAttribute(Constant.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            return paperService.modifyPublicFlag(pkPaper, publicFlag, teacher);
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
    public ServerResponse modifyFlag(HttpSession session, Integer pkPaper, String flag) {
        //判断登录
        Teacher teacher = (Teacher) session.getAttribute(Constant.CURRENT_USER);
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
    public ServerResponse paperDetail(HttpSession session, Integer pkPaper) {
        //判断登录
        Teacher teacher = (Teacher) session.getAttribute(Constant.CURRENT_USER);
        if (teacher == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        //判断权限，业务处理
        if (Constant.Role.ROLE_TEACHER.equals(teacher.getRole())) {
            return paperService.paperDetail(pkPaper, teacher);
        }
        return ServerResponse.createByErrorMessage("不是教师，无法操作");
    }
    //自动组卷

    //手动组卷

    //发布试卷
}
