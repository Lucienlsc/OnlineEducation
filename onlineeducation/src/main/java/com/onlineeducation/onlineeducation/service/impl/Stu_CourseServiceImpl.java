package com.onlineeducation.onlineeducation.service.impl;

import com.onlineeducation.onlineeducation.dao.AdminDao;
import com.onlineeducation.onlineeducation.dao.Stu_CourseDao;
import com.onlineeducation.onlineeducation.entity.Admin;
import com.onlineeducation.onlineeducation.entity.Stu_Course;
import com.onlineeducation.onlineeducation.service.AdminService;
import com.onlineeducation.onlineeducation.service.Stu_CourseService;
import com.onlineeducation.onlineeducation.utils.MD5Util;
import com.onlineeducation.onlineeducation.utils.NumberUtil;
import com.onlineeducation.onlineeducation.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Stu_CourseServiceImpl implements Stu_CourseService {

    @Autowired
    private Stu_CourseDao stu_courseDao;


    @Override
    public int saveStu_Course(Stu_Course stu_course) {
        return stu_courseDao.addStu_Course(stu_course);
    }
}
