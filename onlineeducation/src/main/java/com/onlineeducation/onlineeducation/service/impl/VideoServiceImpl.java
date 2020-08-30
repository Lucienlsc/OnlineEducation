package com.onlineeducation.onlineeducation.service.impl;

import com.onlineeducation.onlineeducation.dao.CourseDao;
import com.onlineeducation.onlineeducation.dao.VideoDao;
import com.onlineeducation.onlineeducation.entity.Course;
import com.onlineeducation.onlineeducation.entity.Teacher;
import com.onlineeducation.onlineeducation.entity.Video;
import com.onlineeducation.onlineeducation.service.CourseService;
import com.onlineeducation.onlineeducation.service.VideoService;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;


    @Override
    public PageResult getVideoPage(PageUtil pageUtil) {
        if(pageUtil.get("teacherID") != null){//是教师端
            //当前页码中的数据列表
            List<Video> videoList = videoDao.findVideoInTeacher(pageUtil);
            //获取总条数 用于计算分页数据
            int totalVideo = videoDao.getTotalVideoInTeacher(pageUtil);
            //封装成PageResult对象
            PageResult pageResult = new PageResult(videoList, totalVideo, pageUtil.getLimit(), pageUtil.getPage());
            return pageResult;
        }
        else{//是管理员端
            //当前页码中的数据列表
            List<Video> videoList = videoDao.findVideoInAdmin(pageUtil);
            //获取总条数 用于计算分页数据
            int totalVideo = videoDao.getTotalVideoInAdmin(pageUtil);
            //封装成PageResult对象
            PageResult pageResult = new PageResult(videoList, totalVideo, pageUtil.getLimit(), pageUtil.getPage());
            return pageResult;
        }

    }

    @Override
    public int checkVideo(Video video) {
        return videoDao.checkVideo(video.getId(),video.getIsChecked());
    }

    @Override
    public int updateVideo(Video video) {
        return videoDao.updateVideo(video);
    }

    @Override
    public int saveVideo(Video video, Teacher teacher) {
        video.setTeacherID(teacher.getId());
        return videoDao.addVideo(video);
    }

    @Override
    public Video queryById(Integer id) {
        return videoDao.findVideoById(id);
    }

    @Override
    public List<Video> selectVideoByCourseName(String courseName) {
        return videoDao.findVideoByCourseName(courseName);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return videoDao.deleteVideoBatch(ids);
    }

    @Override
    public Video getVideoById(Integer id) {
        return videoDao.findVideoById(id);
    }


}
