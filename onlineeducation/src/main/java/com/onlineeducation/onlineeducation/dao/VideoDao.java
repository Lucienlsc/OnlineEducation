package com.onlineeducation.onlineeducation.dao;

import com.onlineeducation.onlineeducation.entity.Course;
import com.onlineeducation.onlineeducation.entity.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VideoDao {

    /**
     * 显示所有视频信息
     * @param param
     * @return
     */
    List<Video> findVideoInTeacher(Map param);

    /**
     * 获取视频总数
     * @param param
     * @return
     */
    int getTotalVideoInTeacher(Map param);

    /**
     * 显示所有视频信息
     * @param param
     * @return
     */
    List<Video> findVideoInAdmin(Map param);

    /**
     * 根据课程名称查找视频
     * @param courseName
     * @return
     */
    List<Video> findVideoByCourseName(String courseName);

    /**
     * 获取视频总数
     * @param param
     * @return
     */
    int getTotalVideoInAdmin(Map param);

    /**
     * 上传视频
     * @param video
     * @return
     */
    int addVideo(Video video);

    /**
     * 审核视频
     * @param videoId
     * @param isChecked
     * @return
     */
    int checkVideo(@Param("videoId") Long videoId, @Param("isChecked") String isChecked);

    /**
     * 通过id查找视频
     * @param id
     * @return
     */
    Video findVideoById(Integer id);

    /**
     * 批量删除
     * @param id
     * @return
     */
    int deleteVideoBatch(Object[] id);

    /**
     * 更新视频(编辑)
     * @param video
     * @return
     */
    int updateVideo(Video video);


}
