package com.onlineeducation.onlineeducation.service;


import com.onlineeducation.onlineeducation.entity.Course;
import com.onlineeducation.onlineeducation.entity.Teacher;
import com.onlineeducation.onlineeducation.entity.Video;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;

import java.util.List;

public interface VideoService {

    /**
     * 分页功能
     *
     * @param pageUtil
     * @return
     */
    PageResult getVideoPage(PageUtil pageUtil);

    /**
     * 审核视频
     * @param video
     * @return
     */
    int checkVideo(Video video);

    /**
     * 更新视频（编辑）
     * @param video
     * @return
     */
    int updateVideo(Video video);

    /**
     * 上传视频
     * @param video
     * @param teacher
     * @return
     */
    int saveVideo(Video video , Teacher teacher);

    /**
     * 通过ID查找视频
     * @param id
     * @return
     */
    Video queryById(Integer id);

    /**
     * 根据课程名称查找视频
     * @param courseName
     * @return
     */
    List<Video> selectVideoByCourseName(String courseName);

    /**
     * 批量删除视频
     * @param ids
     * @return
     */
    int deleteBatch(Integer[] ids);

    Video getVideoById(Integer id);

}
