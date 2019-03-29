package com.bs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 试卷和专业关联
 *
 * @author 暗香
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelPaperMajor {

    /**
     * 关联（试卷&专业）表主键
     */
    private Integer pkRelPaperMajor;

    /**
     * 试卷表外键
     */
    private Integer fkPaper;

    /**
     * 专业表外键
     */
    private Integer fkMajor;

    /**
     * 发布时间
     */
    private Date publishTime;
}