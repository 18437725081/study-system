package com.bs.service;

import com.bs.common.ServerResponse;
import com.bs.dao.TeacherMapper;
import com.bs.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张靖烽
 * @name ManageUserService
 * @description
 * @create 2018-01-03 20:26
 **/
@Service
public class ManageUserService {

    @Autowired
    private TeacherMapper teacherMapper;

    public ServerResponse getTeacherList() {
        List<Teacher> list = teacherMapper.selectAllTeacher();
        return ServerResponse.createBySuccess(list);
    }
}
