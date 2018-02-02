package com.bs.service;

import com.bs.common.ServerResponse;
import com.bs.dao.*;
import com.bs.pojo.*;
import com.bs.util.MD5;
import com.bs.vo.RelTeacherMajorVO;
import com.bs.vo.StudentVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Autowired
    private ManagerMapper managerMapper;

    /**
     * @author 张靖烽
     * @description 管理员登录
     * @createtime 2018-01-12 14:56
     */
    public ServerResponse login(String username, String password) {
        if (StringUtils.isAnyBlank(username, password)) {
            return ServerResponse.createByErrorMessage("登录失败：请检查是否正确填写用户名和密码");
        }
        //检查用户名是否存在
        int resultCount = managerMapper.checkUsername(username);
        //用户名不存在
        if (resultCount <= 0) {
            return ServerResponse.createByErrorMessage("登录失败：用户名不存在");
        }
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

    /**
     * @author 张靖烽
     * @description 查询教师
     * @createtime 2018-01-17 15:01
     */
    public ServerResponse queryTeacher(Teacher teacher, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> list = teacherMapper.queryTeacher(teacher);
        //将密码,找回密码问题、答案置为空
        for (Teacher t : list) {
            t.setPassword(StringUtils.EMPTY);
            t.setQuestion(StringUtils.EMPTY);
            t.setAnswer(StringUtils.EMPTY);
        }
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setList(list);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /**
     * @author 张靖烽
     * @description 新增或修改教师信息
     * @createtime 2018-01-09 13:40
     */
    public ServerResponse addOrModifyTeacher(Teacher teacher, Manager manager) {
        if (teacher != null) {
            //设置创建人和更新人
            teacher.setCreatedBy(manager.getPkManager());
            teacher.setLastUpdatedBy(manager.getPkManager());
            //根据主键是否为空决定新增还是修改
            if (teacher.getPkTeacher() != null) {
                int result = teacherMapper.updateByPrimaryKeySelective(teacher);
                if (result > 0) {
                    return ServerResponse.createBySuccessMessage("修改成功");
                }
                return ServerResponse.createBySuccessMessage("修改失败");
            } else {
                //将密码加密
                teacher.setPassword(MD5.md5EncodeUtf8(teacher.getUsername()));
                //检查用户名是否已存在
                int result = teacherMapper.selectUsername(teacher.getUsername());
                if (result > 0) {
                    return ServerResponse.createBySuccessMessage("用户名已存在");
                }
                teacher.setRole("1");
                result = teacherMapper.insert(teacher);
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
            //将密码,找回密码问题、答案置为空
            teacher.setPassword(StringUtils.EMPTY);
            teacher.setQuestion(StringUtils.EMPTY);
            teacher.setAnswer(StringUtils.EMPTY);
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
        return ServerResponse.createBySuccessMessage("删除失败,该教师不存在或已被删除");
    }

    /**
     * @author 张靖烽
     * @description 获取学生信息
     * @createtime 2018-01-09 14:16
     */
    public ServerResponse getStudentList() {
        List<Student> list = studentMapper.selectAllStudent();

        List<StudentVO> studentVOList = Lists.newArrayList();
        //将密码,找回密码问题、答案置为空
        for (Student s : list) {
            StudentVO studentVO = setStudentVO(s);
            studentVOList.add(studentVO);
        }
        return ServerResponse.createBySuccess(studentVOList);
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
                int result = studentMapper.updateByPrimaryKeySelective(student);
                if (result > 0) {
                    return ServerResponse.createBySuccessMessage("修改成功");
                }
                return ServerResponse.createBySuccessMessage("修改失败");
            } else {
                student.setPassword(MD5.md5EncodeUtf8(student.getPassword()));
                //检查用户名是否已存在
                int result = studentMapper.selectUsername(student.getUsername());
                if (result > 0) {
                    return ServerResponse.createBySuccessMessage("用户名已存在");
                }
                result = studentMapper.insert(student);
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
            student.setPassword(StringUtils.EMPTY);
            student.setQuestion(StringUtils.EMPTY);
            student.setAnswer(StringUtils.EMPTY);
            return ServerResponse.createBySuccess(student);
        }
        return ServerResponse.createByErrorMessage("学生不存在或已被删除");
    }

    /**
     * @author 张靖烽
     * @description 设置StudentVO对象
     * @createtime 2018-01-17 10:51
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
        studentVO.setCreatedTime(student.getCreatedTime());
        studentVO.setLastUpdatedTime(student.getLastUpdatedTime());
        return studentVO;
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
        return ServerResponse.createBySuccessMessage("删除失败，该学生不存在或已被删除");
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
     * @description 获取专业信息&&查询专业
     * @createtime 2018-01-17 15:01
     */
    public ServerResponse queryMajor(Major major, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Major> majorList = majorMapper.queryMajor(major);
        PageInfo pageInfo = new PageInfo(majorList);
        pageInfo.setList(majorList);
        return ServerResponse.createBySuccess(pageInfo);
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

    /**
     * @author 张靖烽
     * @description 重置教师密码
     * @createtime 2018-01-17 11:19
     */
    public ServerResponse resetTeacherPwd(Integer pkTeacher) {
        if (pkTeacher == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        String password = MD5.md5EncodeUtf8("123456");
        Teacher teacher = new Teacher();
        teacher.setPkTeacher(pkTeacher);
        teacher.setPassword(password);
        int result = teacherMapper.updateByPrimaryKeySelective(teacher);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("重置密码成功");
        }
        return ServerResponse.createBySuccessMessage("重置密码失败");
    }

    /**
     * @author 张靖烽
     * @description 重置学生密码
     * @createtime 2018-01-17 11:19
     */
    public ServerResponse resetStudentPwd(Integer pkStudent) {
        if (pkStudent == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        String password = MD5.md5EncodeUtf8("888888");
        Student student = new Student();
        student.setPkStudent(pkStudent);
        student.setPassword(password);
        int result = studentMapper.updateByPrimaryKeySelective(student);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("重置密码成功");
        }
        return ServerResponse.createBySuccessMessage("重置密码失败");
    }

    /**
     * @author 张靖烽
     * @description 查询学生
     * @createtime 2018-01-17 15:01
     */
    public ServerResponse queryStudent(Student student) {
        List<Student> list =  studentMapper.queryStudent(student);
        List<StudentVO> studentVOList = Lists.newArrayList();
        //将密码,找回密码问题、答案置为空
        for (Student s : list) {
            StudentVO studentVO = setStudentVO(s);
            studentVOList.add(studentVO);
        }
        return ServerResponse.createBySuccess(studentVOList);
    }

    /**
     * @author 张靖烽
     * @description 获取年级
     * @createtime 2018-02-01 22:46
     */
    public ServerResponse getGrade() {
        List<String> gradeList = majorMapper.getGrade();
        return ServerResponse.createBySuccess(gradeList);
    }

    /**
     * @author 张靖烽
     * @description 获取指定年级下专业
     * @createtime 2018-02-01 22:46
     */
    public ServerResponse getMajor(String grade) {
        List<Major> majorList = majorMapper.getMajor(grade);
        return ServerResponse.createBySuccess(majorList);
    }

    /**
     * @author 张靖烽
     * @description 获取教师关联的专业信息
     * @createtime 2018-02-02 13:34
     */
    public ServerResponse getTeacherMajor(String pkTeacher,Integer pageNum,Integer pageSize) {
        List<Integer> pkMajorList = relTeacherMajorMapper.selectFkMajorList(pkTeacher);
        if (pkMajorList.size() > 0){
            PageHelper.startPage(pageNum, pageSize);
            List<Major> majorList = majorMapper.selectMajorByPk(pkMajorList);
            PageInfo pageInfo = new PageInfo(majorList);
            pageInfo.setList(majorList);
            return ServerResponse.createBySuccess(pageInfo);
        }
        PageInfo pageInfo = new PageInfo(pkMajorList);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
