package com.onlineeducation.onlineeducation.service;

import com.onlineeducation.onlineeducation.entity.Stu_Course;

public interface Stu_CourseService {

    /**
     * 新增学生课程分配记录
     * @param stu_course
     * @return
     */
    int saveStu_Course(Stu_Course stu_course);


}
