package com.onlineeducation.onlineeducation.service;


import com.onlineeducation.onlineeducation.entity.*;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;

import java.util.List;
import java.util.Map;

public interface CourseService {

    /**
     * 分页功能
     *
     * @param pageUtil
     * @return
     */
    PageResult getCoursePage(PageUtil pageUtil);

    /**
     * 首页显示课程列表
     *
     * @param
     * @return
     */
    List<Course> getCourseList();

    /**
     * 获取教师给学生指定该上的课程
     * @return
     */
    List<Course> getMyCourseList(Integer sid);

    /**
     * 查找当前登录的教师所发布的课程
     * @param tid
     * @return
     */
    List<Course> getTeacherCourseList(Long tid);

    /**
     * 新增课程
     * @param course
     * @param teacher
     * @return
     */
    int saveCourse(Course course , Teacher teacher);

    /**
     * 根据课程名获取课程记录
     * @param courseName
     * @return
     */
    Course selectByCourseName(String courseName);

    /**
     * 审核课程
     * @param course
     * @return
     */
    int checkCourse(Course course);

    /**
     * 更新课程（编辑）
     * @param course
     * @return
     */
    int updateCourse(Course course);

    /**
     * 通过ID查找课程
     * @param id
     * @return
     */
    Course queryById(Integer id);

    /**
     * 批量删除课程
     * @param ids
     * @return
     */
    int deleteBatch(Integer[] ids);



}
