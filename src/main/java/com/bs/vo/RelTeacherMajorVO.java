package com.bs.vo;

import com.bs.pojo.Major;

import java.util.List;

/**
 * @author 张靖烽
 * @name RelTeacherMajorVO
 * @description
 * @create 2018-01-10 9:03
 **/
public class RelTeacherMajorVO {

    private String name;

    private List<Major> majorList;

    @Override
    public String toString() {
        return "RelTeacherMajorVO{" +
                "name='" + name + '\'' +
                ", majorList=" + majorList +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Major> getMajorList() {
        return majorList;
    }

    public void setMajorList(List<Major> majorList) {
        this.majorList = majorList;
    }
}
