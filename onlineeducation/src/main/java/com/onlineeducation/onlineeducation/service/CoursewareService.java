package com.onlineeducation.onlineeducation.service;


import com.onlineeducation.onlineeducation.entity.Courseware;
import com.onlineeducation.onlineeducation.entity.Teacher;
import com.onlineeducation.onlineeducation.entity.Video;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;

import java.util.List;

public interface CoursewareService {

    /**
     * 分页功能
     *
     * @param pageUtil
     * @return
     */
    PageResult getCoursewarePage(PageUtil pageUtil);

    /**
     * 更新视频（编辑）
     * @param courseware
     * @return
     */
    int updateCourseware(Courseware courseware);

    /**
     * 上传视频
     * @param courseware
     * @param teacher
     * @return
     */
    int saveCourseware(Courseware courseware , Teacher teacher);

    /**
     * 根据课程名称查找课件
     * @param courseName
     * @return
     */
    List<Courseware> selectCoursewareByCourseName(String courseName);

    /**
     * 审核课件
     * @param courseware
     * @return
     */
    int checkCourseware(Courseware courseware);

    /**
     * 通过ID查找课件
     * @param id
     * @return
     */
    Courseware queryById(Integer id);

    /**
     * 批量删除课件
     * @param ids
     * @return
     */
    int deleteBatch(Integer[] ids);
}
