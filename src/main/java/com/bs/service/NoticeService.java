package com.bs.service;

import com.bs.dao.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张靖烽
 * @name NoticeService
 * @description
 * @create 2017-12-21 10:07
 **/
@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public List selectNotice(){
        return noticeMapper.selectNotice();
    }

}
