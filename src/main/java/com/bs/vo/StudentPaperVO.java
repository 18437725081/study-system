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
public class StudentPaperVO {
    /**
     * 试卷主键
     */
    private Integer pkPaper;
    /**
     * 试卷名称
     */
    private String paperName;
    /**
     * 发布时间
     */
    private String publicTime;
}
