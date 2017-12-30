package com.bs.service;

import com.bs.common.ServerResponse;
import com.bs.dao.NoticeMapper;
import com.bs.pojo.Manager;
import com.bs.pojo.Notice;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张靖烽
 * @name NoticeService
 * @description
 * @create 2017-12-21 10:07
 **/
@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public List selectNotice() {
        return noticeMapper.selectNotice();
    }

    public ServerResponse manageNotice() {
        List<Notice> list = noticeMapper.selectAllNotice();
        return ServerResponse.createBySuccess(list);
    }

    public ServerResponse addOrModifyNotice(Notice notice, Manager manager) {
        if (notice != null) {
            notice.setCreatedBy(manager.getPkManager());
            notice.setLastUpdatedBy(manager.getPkManager());
            if (notice.getPkNotice() != null) {
                int result = noticeMapper.updateByPrimaryKey(notice);
                if (result > 0) {
                    return ServerResponse.createBySuccess("修改成功");
                }
                return ServerResponse.createBySuccess("修改失败");
            } else {

                int result = noticeMapper.insert(notice);
                if (result > 0) {
                    return ServerResponse.createBySuccess("新增成功");
                }
                return ServerResponse.createBySuccess("新增失败");
            }
        }
        return ServerResponse.createByErrorMessage("参数不正确");
    }

}
