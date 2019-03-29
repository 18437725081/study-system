package com.bs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 暗香
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperDetailVO {
    /**
     * 试卷主键
     */
    private Integer pkPaper;
    /**
     * 试卷名称
     */
    private String paperName;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 总分
     */
    private String score;
    /**
     * 选择题VO
     */
    private List<ChoiceQuestionVO> choiceQuestion;
}
