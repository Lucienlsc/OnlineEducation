package com.onlineeducation.onlineeducation.service.impl;

import com.onlineeducation.onlineeducation.dao.CourseDao;
import com.onlineeducation.onlineeducation.entity.*;
import com.onlineeducation.onlineeducation.service.CourseService;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;



    @Override
    public PageResult getCoursePage(PageUtil pageUtil) {

        if(pageUtil.get("teacherID") != null){//是教师端
            //当前页码中的数据列表
            List<Course> courseList = courseDao.findCourseInTeacher(pageUtil);
            //获取总条数 用于计算分页数据
            int totalCourse = courseDao.getTotalCourseInTeacher(pageUtil);
            //封装成PageResult对象
            PageResult pageResult = new PageResult(courseList, totalCourse, pageUtil.getLimit(), pageUtil.getPage());
            return pageResult;
        }
        else {//是管理员端
            //当前页码中的数据列表
            List<Course> courseList = courseDao.findCourseInAdmin(pageUtil);
            //获取总条数 用于计算分页数据
            int totalCourse = courseDao.getTotalCourseInAdmin(pageUtil);
            //封装成PageResult对象
            PageResult pageResult = new PageResult(courseList, totalCourse, pageUtil.getLimit(), pageUtil.getPage());
            return pageResult;
        }
    }

    @Override
    public List<Course> getCourseList() {
        return courseDao.findCourseList();
    }

    @Override
    public List<Course> getMyCourseList(Integer sid) {
        return courseDao.findMyCourseList(sid);
    }

    @Override
    public List<Course> getTeacherCourseList(Long tid) {
        return courseDao.findTeacherCourses(tid);
    }

    @Override
    public int saveCourse(Course course, Teacher teacher) {
        course.setTeacherID(teacher.getId());
        return courseDao.addCourse(course);
    }

    @Override
    public Course selectByCourseName(String courseName) {
        return courseDao.getCourseByCourseName(courseName);
    }

    @Override
    public int checkCourse(Course course) {
        return courseDao.checkCourse(course.getId(),course.getIsChecked());
    }

    @Override
    public int updateCourse(Course course) {
        return courseDao.updateCourse(course);
    }

    @Override
    public Course queryById(Integer id) {
        return courseDao.findCourseById(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return courseDao.deleteCourseBatch(ids);
    }


}
