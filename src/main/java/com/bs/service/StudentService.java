package com.bs.service;

import com.bs.common.ServerResponse;
import com.bs.common.TokenCache;
import com.bs.dao.*;
import com.bs.pojo.*;
import com.bs.util.BigDecimalUtil;
import com.bs.util.MD5;
import com.bs.util.Time;
import com.bs.vo.ChoiceQuestionVO;
import com.bs.vo.PaperDetailVO;
import com.bs.vo.StudentPaperVO;
import com.bs.vo.StudentVO;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private RelPaperMajorMapper relPaperMajorMapper;

    @Autowired
    private PaperMapper paperMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private PaperDetailMapper paperDetailMapper;

    @Autowired
    private TestsMapper testsMapper;

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
        Student student = studentMapper.login(username, md5Password);
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
        int resultCount = studentMapper.checkAnswer(username, question, answer);
        if (resultCount > 0) {
            //说明问题及问题答案是这个用户的,并且是正确的
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, forgetToken);
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
        if (StringUtils.isBlank(forgetToken)) {
            return ServerResponse.createByErrorMessage("参数错误,token需要传递");
        }
        //检查用户名是否存在
        int result = studentMapper.checkUsername(username);
        //用户名不存在
        if (result <= 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        if (StringUtils.isBlank(token)) {
            return ServerResponse.createByErrorMessage("token无效或者过期");
        }
        if (StringUtils.equals(forgetToken, token)) {
            String md5Password = MD5.md5EncodeUtf8(passwordNew);
            result = studentMapper.updatePasswordByUsername(username, md5Password);
            if (result > 0) {
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }
        } else {
            return ServerResponse.createByErrorMessage("token错误,请重新获取重置密码的token");
        }
        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    /**
     * @author 张靖烽
     * @description 已登录，重置密码
     * @createtime 2018-01-12 13:41
     */
    public ServerResponse<String> resetStudentPassword(String passwordNew, String passwordOld, Student student) {
        //防止横向越权,要校验一下这个用户的旧密码,一定要指定是这个用户.因为我们会查询一个count(1),如果不指定id,那么结果就是true啦count>0;
        int resultCount = studentMapper.checkPassword(MD5.md5EncodeUtf8(passwordOld), student.getPkStudent());
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("旧密码错误");
        }
        student.setPassword(MD5.md5EncodeUtf8(passwordNew));
        student.setLastUpdatedBy(student.getPkStudent());
        int updateCount = studentMapper.updateByPrimaryKeySelective(student);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMessage("密码更新成功");
        }
        return ServerResponse.createByErrorMessage("密码更新失败");
    }

    /**
     * @author 张靖烽
     * @description 设置或更新找回密码问题和答案
     * @createtime 2018-01-12 13:42
     */
    public ServerResponse updateStudentInformation(String question, String answer, Student student) {
        if (StringUtils.isBlank(question) || StringUtils.isBlank(answer)) {
            return ServerResponse.createByErrorMessage("请填写问题和答案");
        }
        Student stu = new Student();
        stu.setPkStudent(student.getPkStudent());
        stu.setQuestion(question);
        stu.setAnswer(answer);
        stu.setLastUpdatedBy(student.getPkStudent());
        int result = studentMapper.updateByPrimaryKeySelective(stu);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("设置找回密码问题答案成功");
        }
        return ServerResponse.createByErrorMessage("设置找回密码问题答案失败");
    }

    /**
     * @author 张靖烽
     * @description 设置StudentVO
     * @createtime 2018-01-17 11:13
     */
    public StudentVO setStudentVO(Student student) {
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
     * @description 查询待完成的试卷
     * @createtime 2018-03-29 17:20
     */
    public ServerResponse getUnfinishedPaper(Student student) {
        //通过专业获取试卷ID和发布时间
        List<RelPaperMajor> relPaperMajorList = relPaperMajorMapper.selectByFkMajor(student.getFkMajor());

        List<StudentPaperVO> list = Lists.newArrayList();

        for (RelPaperMajor relPaperMajor : relPaperMajorList) {
            int count = scoreMapper.selectScoreCount(student.getPkStudent(), relPaperMajor.getFkPaper());
            if (count > 0) {
                continue;
            }
            StudentPaperVO studentPaperVO = new StudentPaperVO();
            studentPaperVO.setPkPaper(relPaperMajor.getFkPaper());
            studentPaperVO.setPublicTime(Time.dateToStr(relPaperMajor.getPublishTime()));
            Paper paper = paperMapper.selectByPrimaryKey(relPaperMajor.getFkPaper());
            studentPaperVO.setPaperName(paper.getPaperName());
            list.add(studentPaperVO);
        }
        return ServerResponse.createBySuccess(list);
    }

    /**
     * @author 张靖烽
     * @description 获取试卷内容
     * @createtime 2018-03-29 19:37
     */
    public ServerResponse getPaperDetail(Integer pkPaper, Student student) {
        //查询试卷是否分配给该专业学生
        int count = relPaperMajorMapper.selectCount(pkPaper, student.getFkMajor());
        if (count < 1) {
            return ServerResponse.createByErrorMessage("无法浏览该试卷");
        }
        //试卷详情对象
        PaperDetailVO paperDetailVO = new PaperDetailVO();
        //选择题list对象
        List<ChoiceQuestionVO> choiceQuestionVOList = Lists.newArrayList();

        String score = "0";
        //根据主键获取试卷信息，设置试卷名称和创建人
        Paper paper = paperMapper.selectByPrimaryKey(pkPaper);
        if (paper != null) {
            paperDetailVO.setPkPaper(pkPaper);
            //试卷名称
            paperDetailVO.setPaperName(paper.getPaperName());
            String createdBy = teacherMapper.selectTeacherName(paper.getCreatedBy());
            //创建人
            paperDetailVO.setCreatedBy(createdBy);
        }
        //根据试卷主键获取list
        List<PaperDetail> paperDetailList = paperDetailMapper.selectPaperDetailByPkPaper(pkPaper);
        if (paperDetailList.size() > 0) {
            for (PaperDetail p : paperDetailList) {
                //累积分数
                score = BigDecimalUtil.add(p.getScore(), score);
                //根据试题类型分别加入对应list
                String type = p.getTestsType();
                if ("1".equals(type)) {
                    setOption(p, choiceQuestionVOList);
                }
            }
        }
        //分数
        paperDetailVO.setScore(String.valueOf(score));
        //选择题list
        paperDetailVO.setChoiceQuestion(choiceQuestionVOList);
        return ServerResponse.createBySuccess(paperDetailVO);
    }

    /**
     * @author 张靖烽
     * @description 选择题拼装
     * @createtime 2018-03-15 9:40
     */
    private void setOption(PaperDetail p, List<ChoiceQuestionVO> choiceQuestionVOList) {
        ChoiceQuestionVO choiceQuestionVO = new ChoiceQuestionVO();
        Tests tests = testsMapper.selectByPrimaryKey(p.getFkTests());
        //拼装choiceQuestionVO对象
        choiceQuestionVO.setPkTest(tests.getPkTest());
        choiceQuestionVO.setTestTitle(tests.getTestTitle());
        String[] contents = tests.getTestContent().split(";");
        choiceQuestionVO.setOptionA(contents[0]);
        choiceQuestionVO.setOptionB(contents[1]);
        choiceQuestionVO.setOptionC(contents[2]);
        choiceQuestionVO.setOptionD(contents[3]);
        choiceQuestionVO.setScore(p.getScore());
        choiceQuestionVOList.add(choiceQuestionVO);
    }

    /**
     * @author 张靖烽
     * @description 学生交卷，计算分数
     * @createtime 2018-03-29 20:25
     */
    public ServerResponse submitPaper(Integer pkPaper, Student student, String testsAndAnswer) {
        Integer scores = 0;

        String[] taas = testsAndAnswer.split(";");
        for (String t : taas) {
            String[] taa = t.split("_");
            if (StringUtils.isNoneBlank(taa[0], taa[1])) {
                int count = testsMapper.checkAnswer(taa[0], taa[1]);
                if (count > 0) {
                    scores += Integer.valueOf(taa[2]);
                }
            } else {
                return ServerResponse.createByErrorMessage("非法请求！！！");
            }
        }
        Score score = new Score();
        score.setFkPaper(pkPaper);
        score.setFkStudent(student.getPkStudent());
        score.setScore(String.valueOf(scores));
        score.setFlag("Y");
        int result = scoreMapper.insert(score);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("交卷成功");
        } else {
            return ServerResponse.createByErrorMessage("交卷失败");
        }
    }
}
