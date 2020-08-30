package com.onlineeducation.onlineeducation.service;

import com.onlineeducation.onlineeducation.entity.Admin;
import com.onlineeducation.onlineeducation.entity.Announcement;
import com.onlineeducation.onlineeducation.entity.Test;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;

public interface AnnouncementService {

    /**
     * 分页功能
     * @param pageUtil
     * @return
     */
    PageResult getAnnouncementPage(PageUtil pageUtil);

    /**
     * 新增公告
     * @param announcement
     * @return
     */
    int saveAnnouncement(Announcement announcement ,Admin admin);

    /**
     * 根据公告标题获取公告信息
     * @param announcementName
     * @return
     */
    Announcement selectByAnnouncementTitle(String announcementName);

    /**
     * 根据主键查询公告
     * @param id
     * @return
     */
    Announcement selectByAnnouncementId(Long id);

    /**
     * 更新公告
     * @param announcement
     * @return
     */
    int updateAnnouncement(Announcement announcement,Admin admin);

    /**
     * 删除测试
     * @param ids
     * @return
     */
    int deleteAnnouncementBatch(Integer[] ids);
}
