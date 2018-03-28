package com.bs.service;

import com.bs.common.ServerResponse;
import com.bs.dao.*;
import com.bs.pojo.*;
import com.bs.util.BigDecimalUtil;
import com.bs.vo.ChoiceQuestionVO;
import com.bs.vo.PaperDetailVO;
import com.bs.vo.PaperTestsVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张靖烽
 * @name PaperService
 * @description
 * @create 2018-03-14 15:50
 **/
@Service
public class PaperService {

    private static final String FLAG_Y = "Y";

    @Autowired
    private PaperMapper paperMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private PaperDetailMapper paperDetailMapper;

    @Autowired
    private TestsMapper testsMapper;

    @Autowired
    private RelTeacherMajorMapper teacherMajorMapper;

    @Autowired
    private RelPaperMajorMapper relPaperMajorMapper;

    /**
     * @author 张靖烽
     * @description 查询试卷
     * @createtime 2018-03-14 19:31
     */
    public ServerResponse queryPaper(Paper paper, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Paper> list = paperMapper.queryPaper(paper);
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setList(list);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /**
     * @author 张靖烽
     * @description 新增试卷
     * @createtime 2018-03-14 19:36
     */
    public ServerResponse addPaper(String paperName, String publicFlag, Teacher teacher) {
        if (StringUtils.isNotBlank(paperName) && StringUtils.isNotBlank(publicFlag)) {
            Paper paper = new Paper();
            paper.setPaperName(paperName);
            paper.setFlagPublic(publicFlag);
            paper.setFlagEdit("Y");
            paper.setFlag("Y");
            paper.setCreatedBy(teacher.getPkTeacher());
            paper.setLastUpdatedBy(teacher.getPkTeacher());
            int result = paperMapper.insert(paper);
            if (result > 0) {
                return ServerResponse.createBySuccessMessage("新增试卷成功");
            }
            return ServerResponse.createByErrorMessage("新增试卷失败");
        }
        return ServerResponse.createByErrorMessage("参数不正确");
    }

    /**
     * @author 张靖烽
     * @description 修改试卷公开状态
     * @createtime 2018-03-14 19:48
     */
    public ServerResponse modifyPublicFlag(Integer pkPaper, String publicFlag, Teacher teacher) {
        if (pkPaper != null && StringUtils.isNotBlank(publicFlag)) {
            int pkTeacher = paperMapper.selectCreatedByPkPaper(pkPaper);
            if (pkTeacher == teacher.getPkTeacher()) {
                Paper paper = new Paper();
                paper.setPkPaper(pkPaper);
                paper.setFlagPublic(publicFlag);
                int result = paperMapper.updateByPrimaryKeySelective(paper);
                if (result > 0) {
                    return ServerResponse.createBySuccessMessage("更改状态成功");
                }
                return ServerResponse.createByErrorMessage("更改状态失败");
            }
            return ServerResponse.createByErrorMessage("无权限修改此试卷");
        }
        return ServerResponse.createByErrorMessage("参数不正确");
    }

    /**
     * @author 张靖烽
     * @description 修改试卷状态
     * @createtime 2018-03-14 19:50
     */
    public ServerResponse modifyFlag(Integer pkPaper, String flag, Teacher teacher) {
        if (pkPaper != null && StringUtils.isNotBlank(flag)) {
            int pkTeacher = paperMapper.selectCreatedByPkPaper(pkPaper);
            if (pkTeacher == teacher.getPkTeacher()) {
                Paper paper = new Paper();
                paper.setPkPaper(pkPaper);
                paper.setFlag(flag);
                int result = paperMapper.updateByPrimaryKeySelective(paper);
                if (result > 0) {
                    return ServerResponse.createBySuccessMessage("更改状态成功");
                }
                return ServerResponse.createByErrorMessage("更改状态失败");
            }
            return ServerResponse.createByErrorMessage("无权限修改此试卷");
        }
        return ServerResponse.createByErrorMessage("参数不正确");
    }

    /**
     * @author 张靖烽
     * @description 试卷预览
     * @createtime 2018-03-14 20:16
     */
    public ServerResponse paperDetail(Integer pkPaper, Teacher teacher) {
        //判断试卷是否公开
        String flag = paperMapper.selectPublicFlag(pkPaper);
        if (!FLAG_Y.equals(flag)) {
            //试卷未公开，判断试卷是否是该用户创建
            int pkTeacher = paperMapper.selectCreatedByPkPaper(pkPaper);
            if (pkTeacher != teacher.getPkTeacher()) {
                return ServerResponse.createByErrorMessage("无权限查看不属于你的未公开试卷");
            }
        }
        //试卷详情对象
        PaperDetailVO paperDetailVO = new PaperDetailVO();
        //选择题list对象
        List<ChoiceQuestionVO> choiceQuestionVOList = Lists.newArrayList();

        String score = "0";
        //根据主键获取试卷信息，设置试卷名称和创建人
        Paper paper = paperMapper.selectByPrimaryKey(pkPaper);
        if (paper != null) {
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
     * @description 手动组卷, 添加试题
     * @createtime 2018-03-15 11:27
     */
    public ServerResponse compositionPaper(PaperDetail paperDetail, Teacher teacher) {
        Integer pkTeacher = paperMapper.selectCreatedByPkPaper(paperDetail.getFkPaper());
        if (pkTeacher == null) {
            return ServerResponse.createByErrorMessage("试卷不存在！");
        }
        //判断试卷是否为该教师创建
        if (teacher.getPkTeacher().equals(pkTeacher)) {
            String editFlag = paperMapper.selectEditFlag(paperDetail.getFkPaper());
            //判断试卷是否发布，发布过的试卷不可编辑
            if (FLAG_Y.equals(editFlag)) {
                int count = paperDetailMapper.selectRepeat(paperDetail.getFkPaper(), paperDetail.getFkTests());
                if (count > 0) {
                    return ServerResponse.createByErrorMessage("试卷已添加该试题，请勿重复添加");
                }
                //获取试题类型
                String type = testsMapper.selectType(paperDetail.getFkTests());
                if (type == null) {
                    return ServerResponse.createByErrorMessage("试题不存在！");
                }
                paperDetail.setTestsType(type);
                int result = paperDetailMapper.insert(paperDetail);
                if (result > 0) {
                    return ServerResponse.createBySuccessMessage("添加试题成功");
                }
                return ServerResponse.createByErrorMessage("添加试题失败");
            }
            return ServerResponse.createByErrorMessage("该试卷不可编辑");
        }
        return ServerResponse.createByErrorMessage("无权限");
    }

    /**
     * @author 张靖烽
     * @description 删除试卷试题
     * @createtime 2018-03-15 19:50
     */
    public ServerResponse deleteTestsFromPaper(Integer fkTest, Integer fkPaper, Teacher teacher) {
        int pkTeacher = paperMapper.selectCreatedByPkPaper(fkPaper);
        //判断试卷是否为该教师创建
        if (pkTeacher == teacher.getPkTeacher()) {
            String editFlag = paperMapper.selectEditFlag(fkPaper);
            //判断试卷是否发布，发布过的试卷不可编辑
            if (FLAG_Y.equals(editFlag)) {
                int result = paperDetailMapper.deleteTestsFromPaper(fkTest, fkPaper);
                if (result > 0) {
                    return ServerResponse.createBySuccessMessage("删除成功");
                }
                return ServerResponse.createByErrorMessage("删除失败");
            }
            return ServerResponse.createByErrorMessage("该试卷不可编辑");
        }
        return ServerResponse.createByErrorMessage("无权限");
    }

    /**
     * @author 张靖烽
     * @description 清空试卷试题
     * @createtime 2018-03-15 19:50
     */
    public ServerResponse emptyTestsFromPaper(Integer fkPaper, Teacher teacher) {
        int pkTeacher = paperMapper.selectCreatedByPkPaper(fkPaper);
        //判断试卷是否为该教师创建
        if (pkTeacher == teacher.getPkTeacher()) {
            String editFlag = paperMapper.selectEditFlag(fkPaper);
            //判断试卷是否发布，发布过的试卷不可编辑
            if (FLAG_Y.equals(editFlag)) {
                int result = paperDetailMapper.emptyTestsFromPaper(fkPaper);
                if (result > 0) {
                    return ServerResponse.createBySuccessMessage("删除成功");
                }
                return ServerResponse.createByErrorMessage("删除失败");
            }
            return ServerResponse.createByErrorMessage("该试卷不可编辑");
        }
        return ServerResponse.createByErrorMessage("无权限");
    }

    /**
     * @author 张靖烽
     * @description 发布试卷
     * @createtime 2018-03-15 20:23
     */
    public ServerResponse assignmentPaper(Integer fkPaper, Integer fkMajor, Teacher teacher) {
        int pkTeacher = paperMapper.selectCreatedByPkPaper(fkPaper);
        //判断试卷是否为该教师创建
        if (pkTeacher == teacher.getPkTeacher()) {
            int count = teacherMajorMapper.selectRelCount(teacher.getPkTeacher(), fkMajor);
            if (count < 1) {
                return ServerResponse.createByErrorMessage("你不是该专业的教师");
            }
            RelPaperMajor relPaperMajor = new RelPaperMajor();
            relPaperMajor.setFkMajor(fkMajor);
            relPaperMajor.setFkPaper(fkPaper);
            int result = relPaperMajorMapper.insert(relPaperMajor);
            if (result > 0) {
                return ServerResponse.createBySuccessMessage("发布成功");
            }
            return ServerResponse.createByErrorMessage("发布失败");
        }
        return ServerResponse.createByErrorMessage("无权限");
    }

    /**
     * @author 张靖烽
     * @description 自动组卷
     * @createtime 2018-03-15 20:29
     */
    public ServerResponse autoBuildPaper(String paperName, String flagPublic,
                                         Integer optionNumber, String optionScore,
                                         String subject, Teacher teacher) {
        //判断参数
        if (StringUtils.isBlank(paperName) || StringUtils.isBlank(subject)) {
            return ServerResponse.createByErrorMessage("试卷名称或学科不能为空");
        }
        //试卷名称是否重复
        int count = paperMapper.selectPaperName(paperName);
        if (count > 0) {
            return ServerResponse.createByErrorMessage("试卷名称重复，请修改");
        }
        //判断题库中试题数量是否大于需求数量
        int number = testsMapper.selectTestsNumber("1",subject);
        if (number < optionNumber) {
            return ServerResponse.createByErrorMessage("题库中该试题的数量为:"+number);
        }
        //新建试卷
        Paper paper = new Paper();
        paper.setPaperName(paperName);
        paper.setFlagPublic(flagPublic);
        paper.setFlagEdit("Y");
        paper.setFlag("Y");
        paper.setCreatedBy(teacher.getPkTeacher());
        paper.setLastUpdatedBy(teacher.getPkTeacher());
        int result = paperMapper.insert(paper);
        if (result > 0) {
            //获取试题list
            Integer pkPaper = paperMapper.selectByPaperName(paperName);
            List<Tests> testsList = testsMapper.randomOptionTests(subject, optionNumber, "1");
            if (testsList != null) {
                for (Tests t : testsList) {
                    PaperDetail paperDetail = new PaperDetail();
                    paperDetail.setFkPaper(pkPaper);
                    paperDetail.setFkTests(t.getPkTest());
                    paperDetail.setTestsType(t.getTestType());
                    paperDetail.setScore(optionScore);
                    paperDetail.setPriority("1");
                    paperDetailMapper.insert(paperDetail);
                }
                return ServerResponse.createBySuccessMessage("组卷完成，试卷名称:"+paperName);
            }
            return ServerResponse.createByErrorMessage("获取试题失败");
        }
        return ServerResponse.createByErrorMessage("试卷生成失败");
    }

    /**
     * @author 张靖烽
     * @description 查询试卷试题
     * @createtime 2018-03-28 9:05
     */
    public ServerResponse selectPaperTests(Integer fkPaper) {
        List<PaperDetail> paperDetailList = paperDetailMapper.selectPaperDetailByPkPaper(fkPaper);
        List<PaperTestsVO> paperTestsVOList = Lists.newArrayList();
        for (PaperDetail paperDetail : paperDetailList) {
            PaperTestsVO paperTestsVO = new PaperTestsVO();
            Tests tests = testsMapper.selectByPrimaryKey(paperDetail.getFkTests());
            paperTestsVO.setPkTest(tests.getPkTest());
            paperTestsVO.setTestTitle(tests.getTestTitle());
            paperTestsVO.setScore(paperDetail.getScore());
            paperTestsVO.setPriority(paperDetail.getPriority());
            paperTestsVOList.add(paperTestsVO);
        }
        return ServerResponse.createBySuccess(paperTestsVOList);
    }
}
