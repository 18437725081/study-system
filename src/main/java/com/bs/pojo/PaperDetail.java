package com.bs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 试卷详情pojo
 *
 * @author 暗香
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperDetail {

    /**
     * 试卷详情表主键
     */
    private Integer pkPaperDetail;

    /**
     * 试卷表外键
     */
    private Integer fkPaper;

    /**
     * 题目表外键
     */
    private Integer fkTests;

    /**
     * 题目类型
     */
    private String testsType;

    /**
     * 分值
     */
    private String score;

    /**
     * 优先级
     */
    private String priority;
}