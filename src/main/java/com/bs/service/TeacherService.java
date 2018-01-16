package com.bs.service;

import com.bs.common.ServerResponse;
import com.bs.common.TokenCache;
import com.bs.dao.TeacherMapper;
import com.bs.pojo.Teacher;
import com.bs.util.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author 张靖烽
 * @name TeacherService
 * @description 教师Service
 * @create 2018-01-12 15:01
 **/
@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * @author 张靖烽
     * @description 教师登录
     * @createtime 2018-01-12 15:32
     */
    public ServerResponse login(String username, String password) {
        if (StringUtils.isAnyBlank(username, password)) {
            return ServerResponse.createByErrorMessage("登录失败：请检查");
        }
        //检查用户名是否存在
        int resultCount = teacherMapper.checkUsername(username);
        //用户名不存在
        if (resultCount <= 0) {
            return ServerResponse.createByErrorMessage("登录失败：用户名不存在");
        }
        //用户名存在，对密码进行加密
        String md5Password = MD5.md5EncodeUtf8(password);
        //检查用户输入的用户名和密码是否匹配
        Teacher teacher = teacherMapper.login(username, password);
        //用户名和密码不匹配
        if (teacher == null) {
            return ServerResponse.createByErrorMessage("登录失败：密码不正确");
        }
        //通过校验，将密码置为空
        teacher.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", teacher);
    }

    /**
     * @author 张靖烽
     * @description 未登录：忘记密码，获取问题
     * @createtime 2018-01-12 13:40
     */
    public ServerResponse<String> selectQuestion(String username) {
        if (username == null) {
            ServerResponse.createByErrorMessage("请填写用户名或检查参数");
        }
        //检查用户名是否存在
        int resultCount = teacherMapper.checkUsername(username);
        //用户名不存在
        if (resultCount <= 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        String question = teacherMapper.selectQuestionByTeacher(username);
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
        int resultCount = teacherMapper.checkAnswer(username,question,answer);
        if(resultCount>0){
            //说明问题及问题答案是这个用户的,并且是正确的
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username,forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题的答案错误");
    }

    /**
     * @author 张靖烽
     * @description 未登录：忘记密码，重置密码
     * @createtime 2018-01-12 13:41
     */
    public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {
        if(StringUtils.isBlank(forgetToken)){
            return ServerResponse.createByErrorMessage("参数错误，请重新尝试");
        }
        //检查用户名是否存在
        int resultCount = teacherMapper.checkUsername(username);
        //用户名不存在
        if (resultCount <= 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if(StringUtils.isBlank(token)){
            return ServerResponse.createByErrorMessage("token无效或者过期");
        }
        if(StringUtils.equals(forgetToken,token)){
            String md5Password  = MD5.md5EncodeUtf8(passwordNew);
            int rowCount = teacherMapper.updatePasswordByUsername(username,md5Password);
            if(rowCount > 0){
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }
        }else{
            return ServerResponse.createByErrorMessage("token错误,请重新获取重置密码的token");
        }
        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    /**
     * @author 张靖烽
     * @description 已登录，重置密码
     * @createtime 2018-01-12 13:41
     */
    public ServerResponse<String> resetTeacherPassword(String passwordNew, String passwordOld, Teacher teacher) {
        //防止横向越权,要校验一下这个用户的旧密码,一定要指定是这个用户.因为我们会查询一个count(1),如果不指定id,那么结果就是true啦count>0;
        int resultCount = teacherMapper.checkPassword(MD5.md5EncodeUtf8(passwordOld),teacher.getPkTeacher());
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("旧密码错误");
        }

        teacher.setPassword(MD5.md5EncodeUtf8(passwordNew));
        int updateCount = teacherMapper.updateByPrimaryKeySelective(teacher);
        if(updateCount > 0){
            return ServerResponse.createBySuccessMessage("密码更新成功");
        }
        return ServerResponse.createByErrorMessage("密码更新失败");
    }

    /**
     * @author 张靖烽
     * @description 设置或更新找回密码问题和答案
     * @createtime 2018-01-12 13:42
     */
    public ServerResponse updateTeacherInformation(String question, String answer, Teacher teacher) {
        if(StringUtils.isBlank(question) || StringUtils.isBlank(answer)){
            return ServerResponse.createByErrorMessage("请填写问题和答案");
        }
        Teacher tea = new Teacher();
        tea.setQuestion(question);
        tea.setAnswer(answer);
        tea.setLastUpdatedBy(teacher.getPkTeacher());
        int result = teacherMapper.updateByPrimaryKeySelective(tea);
        if (result > 0){
            return ServerResponse.createBySuccessMessage("设置找回密码问题答案成功");
        }
        return ServerResponse.createByErrorMessage("设置找回密码问题答案失败");
    }
}
