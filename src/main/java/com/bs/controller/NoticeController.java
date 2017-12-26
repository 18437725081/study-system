package com.bs.controller;

import com.bs.common.ServerResponse;
import com.bs.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 张靖烽
 * @name NoticeController
 * @description
 * @create 2017-12-21 10:06
 **/
@Controller
@RequestMapping("/notice/")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping("showNotice.do")
    @ResponseBody
    public ServerResponse<List> showNotice(){
        List list = noticeService.selectNotice();
        return ServerResponse.createBySuccess(list);
    }


}
