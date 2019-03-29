package com.bs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 暗香
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperTestsVO {
    /**
     * 试题主键
     */
    private Integer pkTest;
    /**
     * 题目
     */
    private String testTitle;
    /**
     * 分值
     */
    private String score;
    /**
     * 优先级
     */
    private String priority;
}
