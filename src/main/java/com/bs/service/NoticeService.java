package com.bs.service;

import com.bs.common.ServerResponse;
import com.bs.dao.NoticeMapper;
import com.bs.pojo.Manager;
import com.bs.pojo.Notice;
import com.bs.util.TimeUtil;
import com.bs.vo.NoticeVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 暗香
 */
@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public List selectNotice() {
        return noticeMapper.selectNotice();
    }

    public ServerResponse queryNotice(Notice notice, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Notice> list = noticeMapper.queryNotice(notice);
        List<NoticeVO> noticeVOList = Lists.newArrayList();
        for (Notice n : list) {
            NoticeVO noticeVO = new NoticeVO();
            noticeVO.setPkNotice(n.getPkNotice());
            noticeVO.setNoticeContent(n.getNoticeContent());
            if ("Y".equals(n.getFlag())) {
                noticeVO.setFlag("有效");
            } else if ("N".equals(n.getFlag())) {
                noticeVO.setFlag("无效");
            }
            noticeVO.setLastUpdatedTime(TimeUtil.dateToStr(n.getLastUpdatedTime()));
            noticeVOList.add(noticeVO);
        }
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setList(noticeVOList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    public ServerResponse addOrModifyNotice(Notice notice, Manager manager) {
        if (notice != null) {
            //通知内容不能为空
            if (StringUtils.isNotBlank(notice.getNoticeContent())) {
                notice.setCreatedBy(manager.getPkManager());
                notice.setLastUpdatedBy(manager.getPkManager());
                //根据主键是否为空决定新增还是修改
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
        }
        return ServerResponse.createByErrorMessage("通知内容不能为空");
    }

    public ServerResponse getNotice(Integer pkNotice) {
        if (pkNotice == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        Notice notice = noticeMapper.selectByPrimaryKey(pkNotice);
        if (notice != null) {
            return ServerResponse.createBySuccess(notice);
        }
        return ServerResponse.createByErrorMessage("通知不存在或已被删除");
    }
}
