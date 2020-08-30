package com.onlineeducation.onlineeducation.dao;

import com.onlineeducation.onlineeducation.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CourseDao {

    /**
     * 显示所有课程信息
     * @param param
     * @return
     */
    List<Course> findCourseInTeacher(Map param);

    /**
     * 获取课程总数
     * @param param
     * @return
     */
    int getTotalCourseInTeacher(Map param);

    /**
     * 根据课程名称查找课程
     * @param courseName
     * @return
     */
    Course getCourseByCourseName(String courseName);

    /**
     * 显示所有课程信息
     * @param param
     * @return
     */
    List<Course> findCourseInAdmin(Map param);

    /**
     * 获取课程总数
     * @param param
     * @return
     */
    int getTotalCourseInAdmin(Map param);

    /**
     * 首页显示所有课程信息
     * @param
     * @return
     */
    List<Course> findCourseList();

    /**
     * 获取教师给学生指定该上的课程
     * @param
     * @return
     */
    List<Course> findMyCourseList(Integer sid);

    /**
     * 查找当前登录教师所发布的课程
     * @param tid
     * @return
     */
    List<Course> findTeacherCourses(Long tid);

    /**
     * 发布公告
     * @param course
     * @return
     */
    int addCourse(Course course);

    /**
     * 审核课程
     * @param courseId
     * @param isChecked
     * @return
     */
    int checkCourse(@Param("courseId") Long courseId, @Param("isChecked") String isChecked);

    /**
     * 通过id查找课程
     * @param id
     * @return
     */
    Course findCourseById(Integer id);

    /**
     * 批量删除
     * @param id
     * @return
     */
    int deleteCourseBatch(Object[] id);



    /**
     * 更新课程(编辑)
     * @param course
     * @return
     */
    int updateCourse(Course course);
}
