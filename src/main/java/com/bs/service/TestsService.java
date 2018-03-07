package com.bs.service;

import com.bs.common.ServerResponse;
import com.bs.dao.TestsMapper;
import com.bs.pojo.Tests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张靖烽
 * @name TestsService
 * @description
 * @create 2018-03-07 19:13
 **/
@Service
public class TestsService {

    @Autowired
    private TestsMapper testsMapper;

    /**
     * @author 张靖烽
     * @description 查询试题
     * @createtime 2018-03-07 19:33
     */
    public ServerResponse queryTests(Tests tests, int pageNum, int pageSize) {
        return null;
    }

    /**
     * @author 张靖烽
     * @description 获取单试题信息
     * @createtime 2018-03-07 19:33
     */
    public ServerResponse getTestsInfo(Integer pkTest) {
        return null;
    }
}
