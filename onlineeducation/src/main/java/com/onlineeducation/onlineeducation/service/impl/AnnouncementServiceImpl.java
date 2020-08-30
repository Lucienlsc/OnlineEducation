package com.onlineeducation.onlineeducation.service.impl;

import com.onlineeducation.onlineeducation.dao.AnnouncementDao;
import com.onlineeducation.onlineeducation.dao.TestDao;
import com.onlineeducation.onlineeducation.entity.Admin;
import com.onlineeducation.onlineeducation.entity.Announcement;
import com.onlineeducation.onlineeducation.entity.Test;
import com.onlineeducation.onlineeducation.service.AnnouncementService;
import com.onlineeducation.onlineeducation.service.TestService;
import com.onlineeducation.onlineeducation.utils.MD5Util;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementDao announcementDao;

    @Override
    public PageResult getAnnouncementPage(PageUtil pageUtil) {
        //当前页码中的数据列表
        List<Announcement> AnnouncementList = announcementDao.findAnnouncement(pageUtil);
        //获取总条数 用于计算分页数据
        int totalAnnouncementNumber = announcementDao.getTotalAnnouncementNumber(pageUtil);
        //封装成PageResult对象
        PageResult pageResult = new PageResult(AnnouncementList, totalAnnouncementNumber, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int saveAnnouncement(Announcement announcement, Admin admin) {
        announcement.setAdminID(admin.getId());
        return announcementDao.addAnnouncement(announcement);
    }

    @Override
    public Announcement selectByAnnouncementTitle(String announcementTitle) {
        return announcementDao.getAnnouncementByAnnouncementTitle(announcementTitle);
    }

    @Override
    public Announcement selectByAnnouncementId(Long id) {
        Announcement announcement =announcementDao.gettAnnouncementById(id);
        if(announcement != null){
            return announcement;
        }
        return null;
    }

    @Override
    public int updateAnnouncement(Announcement announcement,Admin admin) {
        announcement.setAdminID(admin.getId());
        return announcementDao.updateAnnouncement(announcement.getId(), announcement.getTitle(),announcement.getContent(),announcement.getAdminID());
    }

    @Override
    public int deleteAnnouncementBatch(Integer[] ids) {
        return announcementDao.deletetAnnouncementBatch(ids);
    }
}
