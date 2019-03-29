package com.bs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 分数
 *
 * @author 暗香
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {

    /**
     * 成绩表主键
     */
    private Integer pkScore;

    /**
     * 学生表外键
     */
    private Integer fkStudent;

    /**
     * 试卷表外键
     */
    private Integer fkPaper;

    /**
     * 成绩
     */
    private String score;

    /**
     * 完成状态
     */
    private String flag;

    /**
     * 完成时间
     */
    private Date finishTime;
}