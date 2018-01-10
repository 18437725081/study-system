package com.bs.service;

import com.bs.common.ServerResponse;
import com.bs.dao.MajorMapper;
import com.bs.dao.RelTeacherMajorMapper;
import com.bs.dao.StudentMapper;
import com.bs.dao.TeacherMapper;
import com.bs.pojo.*;
import com.bs.vo.RelTeacherMajorVO;
import com.google.common.collect.Lists;
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

    @Autowired
    private RelTeacherMajorMapper relTeacherMajorMapper;

    @Autowired
    private MajorMapper majorMapper;

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
            //创建人和更新人
            teacher.setCreatedBy(manager.getPkManager());
            teacher.setLastUpdatedBy(manager.getPkManager());
            //根据主键是否为空决定新增还是修改
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
        if (pkTeacher == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        Teacher teacher = teacherMapper.selectByPrimaryKey(pkTeacher);
        if (teacher != null) {
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
        if (pkTeacher == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        int result = teacherMapper.deleteByPrimaryKey(pkTeacher);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createBySuccessMessage("删除失败");
    }

    /**
     * @author 张靖烽
     * @description 获取学生信息
     * @createtime 2018-01-09 14:16
     */
    public ServerResponse getStudentList() {
        List<Student> list = studentMapper.selectAllStudent();
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
            //根据主键是否为空决定新增还是修改
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
        if (pkStudent == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        Student student = studentMapper.selectByPrimaryKey(pkStudent);
        if (student != null) {
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
        if (pkStudent == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        int result = studentMapper.deleteByPrimaryKey(pkStudent);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createBySuccessMessage("删除失败");
    }

    /**
     * @author 张靖烽
     * @description 查看教师关联班级
     * @createtime 2018-01-10 8:52
     */
    public ServerResponse getRelTeacherMajor(Integer pkTeacher) {
        if (pkTeacher == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        RelTeacherMajorVO relTeacherMajorVO = new RelTeacherMajorVO();
        Teacher teacher = teacherMapper.selectByPrimaryKey(pkTeacher);
        if (teacher != null) {
            relTeacherMajorVO.setName(teacher.getName());
        } else {
            return ServerResponse.createByErrorMessage("未找到该教师");
        }
        List<Integer> majorFkList = relTeacherMajorMapper.selectFkMajor(pkTeacher);
        if (majorFkList != null && majorFkList.size() > 0) {
            List<Major> majorList = Lists.newArrayList();
            for (Integer fkMajor : majorFkList) {
                Major major = majorMapper.selectByPrimaryKey(fkMajor);
                if (major != null) {
                    majorList.add(major);
                }
            }
            relTeacherMajorVO.setMajorList(majorList);
        }
        return ServerResponse.createBySuccess(relTeacherMajorVO);
    }

    /**
     * @author 张靖烽
     * @description 新增教师关联班级
     * @createtime 2018-01-10 10:13
     */
    public ServerResponse addRelTeacherMajor(Integer pkTeacher, Integer pkMajor, Manager manager) {
        if (pkTeacher == null || pkMajor == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        RelTeacherMajor relTeacherMajor = new RelTeacherMajor();
        relTeacherMajor.setFkTeacher(pkTeacher);
        relTeacherMajor.setFkMajor(pkMajor);
        relTeacherMajor.setCreatedBy(manager.getPkManager());
        relTeacherMajor.setLastUpdatedBy(manager.getPkManager());
        int result = relTeacherMajorMapper.insert(relTeacherMajor);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("新增成功");
        }
        return ServerResponse.createBySuccessMessage("新增失败");
    }

    /**
     * @author 张靖烽
     * @description 删除教师关联班级
     * @createtime 2018-01-10 10:23
     */
    public ServerResponse delRelTeacherMajor(Integer pkTeacher, Integer pkMajor) {
        if (pkTeacher == null || pkMajor == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        int result = relTeacherMajorMapper.delete(pkTeacher, pkMajor);
        if (result > 1) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createBySuccessMessage("删除失败");
    }

    /**
     * @author 张靖烽
     * @description 查看年级专业信息
     * @createtime 2018-01-10 10:53
     */
    public ServerResponse getMajorList() {
        List<Major> majorList = majorMapper.selectAllMajor();
        return ServerResponse.createBySuccess(majorList);
    }

    /**
     * @author 张靖烽
     * @description 查看单条专业信息
     * @createtime 2018-01-10 10:58
     */
    public ServerResponse getMajorInfo(Integer pkMajor) {
        if (pkMajor == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        Major major = majorMapper.selectByPrimaryKey(pkMajor);
        if (major != null) {
            return ServerResponse.createBySuccess(major);
        }
        return ServerResponse.createByErrorMessage("专业不存在或已被删除");
    }

    /**
     * @author 张靖烽
     * @description 新增或修改年级专业信息
     * @createtime 2018-01-10 11:00
     */
    public ServerResponse addOrModifyMajor(Major major, Manager manager) {
        if (major != null) {
            major.setCreatedBy(manager.getPkManager());
            major.setLastUpdatedBy(manager.getPkManager());
            //根据主键是否为空决定新增还是修改
            if (major.getPkMajor() != null) {
                int result = majorMapper.updateByPrimaryKey(major);
                if (result > 0) {
                    return ServerResponse.createBySuccessMessage("修改成功");
                }
                return ServerResponse.createBySuccessMessage("修改失败");
            } else {

                int result = majorMapper.insert(major);
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
     * @description 删除年级专业信息
     * @createtime 2018-01-10 11:01
     */
    public ServerResponse delMajor(Integer pkMajor) {
        if (pkMajor == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        int result = majorMapper.deleteByPrimaryKey(pkMajor);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createBySuccessMessage("删除失败");
    }
}
