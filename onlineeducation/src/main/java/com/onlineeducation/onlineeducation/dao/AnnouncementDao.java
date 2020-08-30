package com.onlineeducation.onlineeducation.dao;

import com.onlineeducation.onlineeducation.entity.Announcement;
import com.onlineeducation.onlineeducation.entity.Test;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AnnouncementDao {

    /**
     * 列表查找公告
     * @param param
     * @return
     */
    List<Announcement> findAnnouncement(Map param);

    /**
     * 发布公告
     * @param announcement
     * @return
     */
    int addAnnouncement(Announcement announcement);

    /**
     * 根据公告标题查找公告
     * @param announcementName
     * @return
     */
    Announcement getAnnouncementByAnnouncementTitle(String announcementName);

    int updateAnnouncement(@Param("announcementId") Long announcementId, @Param("newTitle") String newTitle,
                           @Param("newContent") String newContent,@Param("adminID") Long adminID);

    /**
     * 获取公告总数
     * @param param
     * @return
     */
    int getTotalAnnouncementNumber(Map param);

    /**
     * 根据id获取公告信息
     * @param id
     * @return
     */
    Announcement gettAnnouncementById(Long id);

    /**
     * 批量删除公告
     * @param id
     * @return
     */
    int deletetAnnouncementBatch(Object[] id);
}
