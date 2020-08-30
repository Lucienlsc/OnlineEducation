package com.onlineeducation.onlineeducation.dao;

import com.onlineeducation.onlineeducation.entity.Admin;
import com.onlineeducation.onlineeducation.entity.Stu_Course;
import com.onlineeducation.onlineeducation.entity.Student;
import org.apache.ibatis.annotations.Param;

public interface Stu_CourseDao {

    /**
     * 添加学生课程分配记录
     * @param stu_course
     * @return
     */
    int addStu_Course(Stu_Course stu_course);

}
