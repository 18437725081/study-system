package com.bs.service;

import com.bs.common.ServerResponse;
import com.bs.dao.NoticeMapper;
import com.bs.pojo.Manager;
import com.bs.pojo.Notice;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    /**
     * @author 张靖烽
     * @description 获取有效通知内容，用于首页展示
     * @createtime 2017-12-29 12:44
     */
    public List selectNotice() {
        return noticeMapper.selectNotice();
    }

    /**
     * @author 张靖烽
     * @description 获取所有通知
     * @createtime 2017-12-29 12:44
     */
    public ServerResponse manageNotice() {
        List<Notice> list = noticeMapper.selectAllNotice();
        for (Notice n : list) {
            if ("Y".equals(n.getFlag())){
                n.setFlag("有效");
            }else if ("N".equals(n.getFlag())){
                n.setFlag("无效");
            }
        }
        return ServerResponse.createBySuccess(list);
    }

    /**
     * @author 张靖烽
     * @description 新增或更新通知
     * @createtime 2017-12-29 12:51
     */
    public ServerResponse addOrModifyNotice(Notice notice, Manager manager) {
        if (notice != null) {
            notice.setCreatedBy(manager.getPkManager());
            notice.setLastUpdatedBy(manager.getPkManager());
            if (notice.getPkNotice() != null) {
                int result = noticeMapper.updateByPrimaryKey(notice);
                if (result > 0) {
                    return ServerResponse.createBySuccessMessage("修改成功");
                }
                return ServerResponse.createBySuccessMessage("修改失败");
            } else {

                int result = noticeMapper.insert(notice);
                if (result > 0) {
                    return ServerResponse.createBySuccessMessage("新增成功");
                }
                return ServerResponse.createBySuccessMessage("新增失败");
            }
        }
        return ServerResponse.createByErrorMessage("参数不正确");
    }

    /**
     * @author 张靖烽
     * @description 获取单条通知内容
     * @createtime 2018-01-04 8:56
     */
    public ServerResponse getNotice(Integer pkNotice) {
        if (pkNotice == null){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        Notice notice = noticeMapper.selectByPrimaryKey(pkNotice);
        if (notice != null){
            return ServerResponse.createBySuccess(notice);
        }
        return ServerResponse.createByErrorMessage("通知不存在或已被删除");
    }
}
