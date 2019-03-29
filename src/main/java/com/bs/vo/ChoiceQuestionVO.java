package com.bs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 问题选择
 *
 * @author 暗香
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceQuestionVO {

    /**
     * 试题表主键
     */
    private Integer pkTest;
    /**
     * 试题类型
     */
    private String testType;
    /**
     * 试题所属学科
     */
    private String testSubject;
    /**
     * 试题题目
     */
    private String testTitle;
    /**
     * 选项A
     */
    private String optionA;
    /**
     * 选项B
     */
    private String optionB;
    /**
     * 选项C
     */
    private String optionC;
    /**
     * 选项D
     */
    private String optionD;
    /**
     * 试题答案
     */
    private String testAnswer;
    /**
     * 试题解析
     */
    private String testAnalyze;
    /**
     * 是否有效
     */
    private String flag;
    /**
     * 分值
     */
    private String score;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 最后一次更新时间
     */
    private String lastUpdatedTime;
}
