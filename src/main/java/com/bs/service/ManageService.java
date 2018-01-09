package com.bs.service;

import com.bs.common.ServerResponse;
import com.bs.dao.StudentMapper;
import com.bs.dao.TeacherMapper;
import com.bs.pojo.Manager;
import com.bs.pojo.Notice;
import com.bs.pojo.Student;
import com.bs.pojo.Teacher;
import org.apache.commons.lang3.StringUtils;
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
public class ManageService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

    /**
     * @author 张靖烽
     * @description 获取教师信息
     * @createtime 2018-01-09 13:40
     */
    public ServerResponse getTeacherList() {
        List<Teacher> list = teacherMapper.selectAllTeacher();
        return ServerResponse.createBySuccess(list);
    }

    /**
     * @author 张靖烽
     * @description 新增或修改教师信息
     * @createtime 2018-01-09 13:40
     */
    public ServerResponse addOrModifyTeacher(Teacher teacher, Manager manager) {
        if (teacher != null) {
            teacher.setCreatedBy(manager.getPkManager());
            teacher.setLastUpdatedBy(manager.getPkManager());
            if (teacher.getPkTeacher() != null) {
                int result = teacherMapper.updateByPrimaryKey(teacher);
                if (result > 0) {
                    return ServerResponse.createBySuccessMessage("修改成功");
                }
                return ServerResponse.createBySuccessMessage("修改失败");
            } else {

                int result = teacherMapper.insert(teacher);
                if (result > 0) {
                    return ServerResponse.createBySuccessMessage("新增成功");
                }
                return ServerResponse.createBySuccessMessage("新增失败");
            }
        }
        return ServerResponse.createByErrorMessage("参数不正确");
    }

    /**
     * @author 张靖烽
     * @description 获取单条教师信息
     * @createtime 2018-01-09 13:55
     */
    public ServerResponse getTeacherInfo(Integer pkTeacher) {
        if (pkTeacher == null){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        Teacher teacher = teacherMapper.selectByPrimaryKey(pkTeacher);
        if (teacher != null){
            return ServerResponse.createBySuccess(teacher);
        }
        return ServerResponse.createByErrorMessage("教师不存在或已被删除");
    }

    /**
     * @author 张靖烽
     * @description 删除教师
     * @createtime 2018-01-09 14:00
     */
    public ServerResponse delTeacher(Integer pkTeacher) {
        if (pkTeacher == null){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        int result = teacherMapper.deleteByPrimaryKey(pkTeacher);
        if (result > 1){
            return ServerResponse.createBySuccessMessage("删除成功");
        } else {
            return ServerResponse.createBySuccessMessage("删除失败");
        }
    }

    /**
     * @author 张靖烽
     * @description 获取学生信息
     * @createtime 2018-01-09 14:16
     */
    public ServerResponse getStudentList() {
        List<Student> list = studentMapper.selectAllTeacher();
        return ServerResponse.createBySuccess(list);
    }

    /**
     * @author 张靖烽
     * @description 新增或修改学生信息
     * @createtime 2018-01-09 20:35
     */
    public ServerResponse addOrModifyStudent(Student student, Manager manager) {
        if (student != null) {
            student.setCreatedBy(manager.getPkManager());
            student.setLastUpdatedBy(manager.getPkManager());
            if (student.getPkStudent() != null) {
                int result = studentMapper.updateByPrimaryKey(student);
                if (result > 0) {
                    return ServerResponse.createBySuccessMessage("修改成功");
                }
                return ServerResponse.createBySuccessMessage("修改失败");
            } else {

                int result = studentMapper.insert(student);
                if (result > 0) {
                    return ServerResponse.createBySuccessMessage("新增成功");
                }
                return ServerResponse.createBySuccessMessage("新增失败");
            }
        }
        return ServerResponse.createByErrorMessage("参数不正确");
    }

    /**
     * @author 张靖烽
     * @description 获取单条学生信息
     * @createtime 2018-01-09 20:40
     */
    public ServerResponse getStudentInfo(Integer pkStudent) {
        if (pkStudent == null){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        Student student = studentMapper.selectByPrimaryKey(pkStudent);
        if (student != null){
            return ServerResponse.createBySuccess(student);
        }
        return ServerResponse.createByErrorMessage("教师不存在或已被删除");
    }

    /**
     * @author 张靖烽
     * @description 删除学生
     * @createtime 2018-01-09 20:42
     */
    public ServerResponse delStudent(Integer pkStudent) {
        if (pkStudent == null){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        int result = studentMapper.deleteByPrimaryKey(pkStudent);
        if (result > 1){
            return ServerResponse.createBySuccessMessage("删除成功");
        } else {
            return ServerResponse.createBySuccessMessage("删除失败");
        }
    }
}
