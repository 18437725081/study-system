package com.bs.vo;

import com.bs.pojo.Major;
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
public class RelTeacherMajorVO {

    /**
     * 教师名称
     */
    private String name;

    /**
     * 专业列表
     */
    private List<Major> majorList;
}
