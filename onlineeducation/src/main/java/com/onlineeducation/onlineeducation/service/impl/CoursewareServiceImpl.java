package com.onlineeducation.onlineeducation.service.impl;

import com.onlineeducation.onlineeducation.dao.CoursewareDao;
import com.onlineeducation.onlineeducation.entity.Courseware;
import com.onlineeducation.onlineeducation.entity.Teacher;
import com.onlineeducation.onlineeducation.service.CoursewareService;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursewareServiceImpl implements CoursewareService {

    @Autowired
    private CoursewareDao coursewareDao;


    @Override
    public PageResult getCoursewarePage(PageUtil pageUtil) {
        if(pageUtil.get("teacherID") != null) {//是教师端
            //当前页码中的数据列表
            List<Courseware> coursewareList = coursewareDao.findCoursewareInTeacher(pageUtil);
            //获取总条数 用于计算分页数据
            int totalCourseware = coursewareDao.getTotalCoursewareInTeacher(pageUtil);
            //封装成PageResult对象
            PageResult pageResult = new PageResult(coursewareList, totalCourseware, pageUtil.getLimit(), pageUtil.getPage());
            return pageResult;
        }
        else {//是管理员端
            //当前页码中的数据列表
            List<Courseware> coursewareList = coursewareDao.findCoursewareInAdmin(pageUtil);
            //获取总条数 用于计算分页数据
            int totalCourseware = coursewareDao.getTotalCoursewareInAdmin(pageUtil);
            //封装成PageResult对象
            PageResult pageResult = new PageResult(coursewareList, totalCourseware, pageUtil.getLimit(), pageUtil.getPage());
            return pageResult;
        }
    }

    @Override
    public int updateCourseware(Courseware courseware) {
        return coursewareDao.updateCourseware(courseware);
    }

    @Override
    public int saveCourseware(Courseware courseware, Teacher teacher) {
        courseware.setTeacher_ID(teacher.getId());
        return coursewareDao.addCourseware(courseware);
    }

    @Override
    public List<Courseware> selectCoursewareByCourseName(String courseName) {
        return coursewareDao.findCoursewareByCourseName(courseName);
    }

    @Override
    public int checkCourseware(Courseware courseware) {
        return coursewareDao.checkCourseware(courseware.getId(),courseware.getIsChecked());
    }

    @Override
    public Courseware queryById(Integer id) {
        return coursewareDao.findCoursewareById(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return coursewareDao.deleteCoursewareBatch(ids);
    }


}
