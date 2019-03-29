package com.bs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 试题
 *
 * @author 暗香
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tests {
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
     * 试题内容
     */
    private String testContent;
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
     * 创建人
     */
    private Integer createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 最后一次更新人
     */
    private Integer lastUpdatedBy;
    /**
     * 最后一次更新时间
     */
    private Date lastUpdatedTime;
}