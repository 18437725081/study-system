package com.bs.service;

import com.bs.common.ServerResponse;
import com.bs.dao.StudentMapper;
import com.bs.pojo.Student;
import com.bs.util.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张靖烽
 * @name StudentService
 * @description 学生Service
 * @create 2018-01-12 15:01
 **/
@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * @author 张靖烽
     * @description 学生登录
     * @createtime 2018-01-12 15:32
     */
    public ServerResponse login(String username, String password) {
        if (StringUtils.isAnyBlank(username, password)) {
            return ServerResponse.createByErrorMessage("登录失败：请检查");
        }
        //检查用户名是否存在
        int resultCount = studentMapper.checkUsername(username);
        //用户名不存在
        if (resultCount <= 0) {
            return ServerResponse.createByErrorMessage("登录失败：用户名不存在");
        }
        //用户名存在，对密码进行加密
        String md5Password = MD5.md5EncodeUtf8(password);
        //检查用户输入的用户名和密码是否匹配
        Student student = studentMapper.login(username, password);
        //用户名和密码不匹配
        if (student == null) {
            return ServerResponse.createByErrorMessage("登录失败：密码不正确");
        }
        //通过校验，将密码置为空
        student.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", student);
    }

    /**
     * @author 张靖烽
     * @description 未登录：忘记密码，获取问题
     * @createtime 2018-01-12 13:40
     */
    public ServerResponse<String> selectQuestion(String username) {
        if (username == null) {
            ServerResponse.createByErrorMessage("请填写用户名");
        }
        //检查用户名是否存在
        int resultCount = studentMapper.checkUsername(username);
        //用户名不存在
        if (resultCount <= 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        String question = studentMapper.selectQuestionByStudent(username);
        if (StringUtils.isNotBlank(question)) {
            return ServerResponse.createBySuccess(question);
        }
        return ServerResponse.createByErrorMessage("找回密码的问题是空的");
    }

    /**
     * @author 张靖烽
     * @description 未登录：忘记密码，检查答案是否正确
     * @createtime 2018-01-12 13:40
     */
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        return null;
    }

    /**
     * @author 张靖烽
     * @description 未登录：忘记密码，重置密码
     * @createtime 2018-01-12 13:41
     */
    public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {
        return null;
    }

    /**
     * @author 张靖烽
     * @description 已登录，重置密码
     * @createtime 2018-01-12 13:41
     */
    public ServerResponse<String> resetStudentPassword(String passwordNew, String passwordOld, Student student) {
        return null;
    }

    /**
     * @author 张靖烽
     * @description 设置或更新找回密码问题和答案
     * @createtime 2018-01-12 13:42
     */
    public ServerResponse updateStudentInformation(String question, String answer, Student student) {
        return null;
    }
}
