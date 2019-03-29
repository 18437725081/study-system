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
public class ScoreDisplayVO {

    private String studentName;

    private String studentId;

    private String major;

    private String paperName;

    private String score;
}
