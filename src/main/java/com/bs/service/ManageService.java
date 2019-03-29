package com.bs.service;

import com.bs.common.Constant;
import com.bs.common.ServerResponse;
import com.bs.dao.*;
import com.bs.pojo.*;
import com.bs.util.Md5Util;
import com.bs.vo.RelTeacherMajorVO;
import com.bs.vo.StudentVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import javafx.scene.chart.ChartBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    private ManagerMapper managerMapper;

    /**
     * 校验教师是否存在
     */
    private static final String CHECK_TEACHER = "check_teacher";

    /**
     * 校验学生是否存在
     */
    private static final String CHECK_STUDENT = "check_student";


    /**
     * 登录操作
     *
     * @param username
     * @param password
     * @return
     */
    public ServerResponse login(String username, String password) {
        if (StringUtils.isAnyBlank(username, password)) {
            return ServerResponse.createByErrorMessage("请检查是否正确填写用户名和密码");
        }
        int resultCount = managerMapper.checkUsername(username);
        if (resultCount <= 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        Manager manager = managerMapper.login(username, password);
        if (Objects.isNull(manager)) {
            return ServerResponse.createByErrorMessage("密码不正确");
        }
        manager.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", manager);
    }

    /**
     * 查询教师
     *
     * @param teacher
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse queryTeacher(Teacher teacher, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> list = teacherMapper.queryTeacher(teacher);
        for (Teacher t : list) {
            t.setPassword(StringUtils.EMPTY);
            t.setQuestion(StringUtils.EMPTY);
            t.setAnswer(StringUtils.EMPTY);
        }
        PageInfo<Teacher> pageInfo = new PageInfo<>(list);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /**
     * 新增教师
     *
     * @param teacher
     * @param manager
     * @return
     */
    public ServerResponse addOrModifyTeacher(Teacher teacher, Manager manager) {
        if (Objects.isNull(teacher)) {
            return ServerResponse.createByErrorMessage("参数不正确");
        }
        teacher.setLastUpdatedBy(manager.getPkManager());
        if (teacher.getPkTeacher() != null) {
            Teacher tea = teacherMapper.selectByPrimaryKey(teacher.getPkTeacher());
            if (!Objects.equals(tea.getUsername(), teacher.getUsername())) {
                boolean exist = checkExistByUserName(teacher.getUsername(), CHECK_TEACHER);
                if (exist) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            teacherMapper.updateByPrimaryKeySelective(teacher);
            return ServerResponse.createBySuccessMessage("修改成功");
        } else {
            teacher.setCreatedBy(manager.getPkManager());
            boolean exist = checkExistByUserName(teacher.getUsername(), CHECK_TEACHER);
            if (exist) {
                return ServerResponse.createByErrorMessage("用户名已存在");
            }
            teacher.setPassword(Md5Util.md5EncodeUtf8(teacher.getUsername()));
            teacher.setRole("1");
            teacherMapper.insert(teacher);
            return ServerResponse.createBySuccessMessage("新增成功");
        }
    }

    /**
     * 获取教师详情
     *
     * @param pkTeacher
     * @return
     */
    public ServerResponse getTeacherInfo(Integer pkTeacher) {
        if (pkTeacher == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        Teacher teacher = teacherMapper.selectByPrimaryKey(pkTeacher);
        if (teacher != null) {
            teacher.setPassword(StringUtils.EMPTY);
            teacher.setQuestion(StringUtils.EMPTY);
            teacher.setAnswer(StringUtils.EMPTY);
            return ServerResponse.createBySuccess(teacher);
        }
        return ServerResponse.createByErrorMessage("教师不存在或已被删除");
    }

    /**
     * 删除教师
     *
     * @param pkTeacher
     * @return
     */
    public ServerResponse delTeacher(Integer pkTeacher) {
        if (pkTeacher == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        teacherMapper.deleteByPrimaryKey(pkTeacher);
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    /**
     * 新增或修改学生信息
     *
     * @param student
     * @param manager
     * @return
     */
    public ServerResponse addOrModifyStudent(Student student, Manager manager) {
        if (student != null) {
            student.setLastUpdatedBy(manager.getPkManager());
            if (student.getPkStudent() != null) {
                Student stu = studentMapper.selectByPrimaryKey(student.getPkStudent());
                if (!Objects.equals(stu.getUsername(), student.getUsername())) {
                    boolean exist = checkExistByUserName(student.getUsername(), CHECK_STUDENT);
                    if (exist) {
                        return ServerResponse.createByErrorMessage("用户名已存在");
                    }
                }
                studentMapper.updateByPrimaryKeySelective(student);
                return ServerResponse.createBySuccessMessage("修改成功");
            } else {
                boolean exist = checkExistByUserName(student.getUsername(), CHECK_STUDENT);
                if (exist) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
                student.setCreatedBy(manager.getPkManager());
                student.setPassword(Md5Util.md5EncodeUtf8(student.getUsername()));
                student.setRole(Constant.Role.ROLE_STUDENT);
                studentMapper.insert(student);
                return ServerResponse.createBySuccessMessage("新增成功");
            }
        }
        return ServerResponse.createByErrorMessage("参数不正确");
    }

    /**
     * 校验用户名是否存在
     *
     * @param username 用户名
     */
    private boolean checkExistByUserName(String username, String checkObj) {
        Integer result;
        if (checkObj.equalsIgnoreCase(CHECK_STUDENT)) {
            result = studentMapper.selectUsername(username);
        } else {
            result = teacherMapper.selectUsername(username);
        }
        return result > 0;
    }

    /**
     * 获取学生信息
     *
     * @param pkStudent
     * @return
     */
    public ServerResponse getStudentInfo(Integer pkStudent) {
        if (pkStudent == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        Student student = studentMapper.selectByPrimaryKey(pkStudent);
        if (student != null) {
            student.setPassword(StringUtils.EMPTY);
            student.setQuestion(StringUtils.EMPTY);
            student.setAnswer(StringUtils.EMPTY);
            StudentVO studentVO = this.setStudentVO(student);
            return ServerResponse.createBySuccess(studentVO);
        }
        return ServerResponse.createByErrorMessage("学生不存在或已被删除");
    }

    /**
     * 封装对象数据
     *
     * @param student
     * @return
     */
    private StudentVO setStudentVO(Student student) {
        StudentVO studentVO = new StudentVO();
        studentVO.setPkStudent(student.getPkStudent());
        studentVO.setUsername(student.getUsername());
        studentVO.setName(student.getName());
        studentVO.setStudentId(student.getStudentId());
        Major major = majorMapper.selectByPrimaryKey(student.getFkMajor());
        if (major != null) {
            studentVO.setMajor(major.getMajor());
            studentVO.setGrade(major.getGrade());
        }
        studentVO.setPkMajor(String.valueOf(student.getFkMajor()));
        studentVO.setCreatedTime(student.getCreatedTime());
        studentVO.setLastUpdatedTime(student.getLastUpdatedTime());
        return studentVO;
    }

    /**
     * 删除学生
     *
     * @param pkStudent
     * @return
     */
    public ServerResponse delStudent(Integer pkStudent) {
        if (pkStudent == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        studentMapper.deleteByPrimaryKey(pkStudent);
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    /**
     * 查看教师关联的专业
     *
     * @param pkTeacher
     * @return
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
        if (!majorFkList.isEmpty()) {
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
     * 教师关联专业保存
     *
     * @param pkTeacher 教师ID
     * @param pkMajor   专业ID
     * @param manager   管理员
     * @return
     */
    public ServerResponse addRelTeacherMajor(Integer pkTeacher, Integer pkMajor, Manager manager) {
        if (pkTeacher == null || pkMajor == null) {
            return ServerResponse.createByErrorMessage("请选择年级和专业！");
        }
        int result = relTeacherMajorMapper.selectRelCount(pkTeacher, pkMajor);
        if (result > 0) {
            return ServerResponse.createByErrorMessage("操作失败：教师和专业已关联");
        }
        RelTeacherMajor relTeacherMajor = new RelTeacherMajor();
        relTeacherMajor.setFkTeacher(pkTeacher);
        relTeacherMajor.setFkMajor(pkMajor);
        relTeacherMajor.setCreatedBy(manager.getPkManager());
        relTeacherMajor.setLastUpdatedBy(manager.getPkManager());
        relTeacherMajorMapper.insert(relTeacherMajor);
        return ServerResponse.createBySuccessMessage("关联成功");
    }

    /**
     * 取消教师关联专业
     *
     * @param pkTeacher
     * @param pkMajor
     * @return
     */
    public ServerResponse delRelTeacherMajor(Integer pkTeacher, Integer pkMajor) {
        if (pkTeacher == null || pkMajor == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        relTeacherMajorMapper.delete(pkTeacher, pkMajor);
        return ServerResponse.createBySuccessMessage("删除关联成功");
    }

    /**
     * 获取专业列表
     *
     * @param major    专业对象
     * @param pageNum  页码
     * @param pageSize 页长
     * @return
     */
    public ServerResponse queryMajor(Major major, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Major> majorList = majorMapper.queryMajor(major);
        PageInfo<Major> pageInfo = new PageInfo<>(majorList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /**
     * 查看单条专业信息
     *
     * @param pkMajor
     * @return
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
     * 添加修改专业
     *
     * @param major
     * @param manager
     * @return
     */
    public ServerResponse addOrModifyMajor(Major major, Manager manager) {
        if (major != null) {
            int result = majorMapper.selectMajorCount(major);
            if (result > 0) {
                return ServerResponse.createByErrorMessage("操作失败：该专业已存在");
            }
            major.setLastUpdatedBy(manager.getPkManager());
            if (major.getPkMajor() != null) {
                majorMapper.updateByPrimaryKey(major);
                return ServerResponse.createBySuccessMessage("修改专业信息成功");
            } else {
                major.setCreatedBy(manager.getPkManager());
                majorMapper.insert(major);
                return ServerResponse.createBySuccessMessage("新增专业信息成功");
            }
        }
        return ServerResponse.createByErrorMessage("参数不正确，请确认");
    }


    /**
     * 删除专业信息
     *
     * @param pkMajor
     * @return
     */
    public ServerResponse delMajor(Integer pkMajor) {
        if (pkMajor == null) {
            return ServerResponse.createByErrorMessage("参数不正确，请确认");
        }
        majorMapper.deleteByPrimaryKey(pkMajor);
        return ServerResponse.createBySuccessMessage("删除专业信息成功");
    }

    /**
     * 重置教师密码
     *
     * @param pkTeacher
     * @return
     */
    public ServerResponse resetTeacherPwd(Integer pkTeacher) {
        if (pkTeacher == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        String password = Md5Util.md5EncodeUtf8("123456");
        Teacher teacher = new Teacher();
        teacher.setPkTeacher(pkTeacher);
        teacher.setPassword(password);
        teacherMapper.updateByPrimaryKeySelective(teacher);
        return ServerResponse.createBySuccessMessage("重置密码成功");
    }

    /**
     * 重置学生密码
     *
     * @param pkStudent
     * @return
     */
    public ServerResponse resetStudentPwd(Integer pkStudent) {
        if (pkStudent == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        String password = Md5Util.md5EncodeUtf8("111111");
        Student student = new Student();
        student.setPkStudent(pkStudent);
        student.setPassword(password);
        studentMapper.updateByPrimaryKeySelective(student);
        return ServerResponse.createBySuccessMessage("重置密码成功");
    }

    /**
     * 查询学生列表
     *
     * @param student
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse queryStudent(Student student, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> list = studentMapper.queryStudent(student);
        List<StudentVO> studentVOList = Lists.newArrayList();
        for (Student s : list) {
            StudentVO studentVO = setStudentVO(s);
            studentVOList.add(studentVO);
        }
        PageInfo<StudentVO> pageInfo = new PageInfo<>(studentVOList);
        pageInfo.setList(studentVOList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /**
     * 获取年级
     *
     * @return
     */
    public ServerResponse getGrade() {
        List<String> gradeList = majorMapper.getGrade();
        return ServerResponse.createBySuccess(gradeList);
    }

    /**
     * 获取年级下的专业
     *
     * @param grade
     * @return
     */
    public ServerResponse getMajor(String grade) {
        List<Major> majorList = majorMapper.getMajor(grade);
        return ServerResponse.createBySuccess(majorList);
    }

    /**
     * 获取教室关联的专业信息
     *
     * @param pkTeacher
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse getTeacherMajor(Integer pkTeacher, Integer pageNum, Integer pageSize) {
        List<Integer> pkMajorList = relTeacherMajorMapper.selectFkMajorList(pkTeacher);
        if (pkMajorList.size() > 0) {
            PageHelper.startPage(pageNum, pageSize);
            List<Major> majorList = majorMapper.selectMajorByPk(pkMajorList);
            PageInfo<Major> pageInfo = new PageInfo<>(majorList);
            return ServerResponse.createBySuccess(pageInfo);
        }
        return ServerResponse.createBySuccess(new PageInfo<>(Collections.emptyList()));
    }
}
