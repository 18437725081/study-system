package com.bs.service;

import com.bs.common.ServerResponse;
import com.bs.dao.PaperDetailMapper;
import com.bs.dao.PaperMapper;
import com.bs.dao.TeacherMapper;
import com.bs.dao.TestsMapper;
import com.bs.pojo.Paper;
import com.bs.pojo.PaperDetail;
import com.bs.pojo.Teacher;
import com.bs.pojo.Tests;
import com.bs.util.BigDecimalUtil;
import com.bs.vo.ChoiceQuestionVO;
import com.bs.vo.PaperDetailVO;
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


        choiceQuestionVOList.add(choiceQuestionVO);
    }
}
