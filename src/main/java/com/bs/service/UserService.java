package com.bs.service;

import com.bs.common.Constant;
import com.bs.common.ServerResponse;
import com.bs.dao.ManagerMapper;
import com.bs.pojo.Manager;
import com.bs.util.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张靖烽
 * @name UserService
 * @description
 * @create 2017-12-26 11:06
 **/
@Service
public class UserService {

    @Autowired
    private ManagerMapper managerMapper;

    public ServerResponse login(String username, String password, String role) {
        if (StringUtils.isAnyBlank(username,password,role)){
            return ServerResponse.createByErrorMessage("登录失败：请检查");
        }
        if (Constant.Role.ROLE_ADMIN.equals(role)){
            //检查用户名是否存在
            int resultCount = managerMapper.checkUsername(username);
            //用户名不存在
            if (resultCount <= 0) {
                return ServerResponse.createByErrorMessage("登录失败：用户名不存在");
            }
            //用户名存在，对密码进行加密
            //String md5Password = MD5.md5EncodeUtf8(password);
            //检查用户输入的用户名和密码是否匹配
            Manager manager = managerMapper.login(username, password);
            //用户名和密码不匹配
            if (manager == null) {
                return ServerResponse.createByErrorMessage("登录失败：密码不正确");
            }
            //通过校验，将密码置为空
            manager.setPassword(StringUtils.EMPTY);
            return ServerResponse.createBySuccess("登录成功", manager);
        }
        if (Constant.Role.ROLE_TEACHER.equals(role)){

        }
        if (Constant.Role.ROLE_STUDENT.equals(role)){

        }
        return ServerResponse.createByErrorMessage("登录失败");
    }




}
