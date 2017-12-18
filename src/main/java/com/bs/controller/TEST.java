package com.bs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 张靖烽
 * @name TEST
 * @description
 * @create 2017-12-18 14:21
 **/
@Controller
public class TEST {

    @RequestMapping("/login.do")
    public String aaa(){
        return "login";
    }

}
