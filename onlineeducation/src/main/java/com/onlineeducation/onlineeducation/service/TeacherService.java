package com.onlineeducation.onlineeducation.service;

import com.onlineeducation.onlineeducation.entity.Admin;
import com.onlineeducation.onlineeducation.entity.Student;
import com.onlineeducation.onlineeducation.entity.Teacher;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;

public interface TeacherService {

    /**
     * 教师登录
     * @param teacherJobNum
     * @param password
     * @return
     */
    Teacher login(String teacherJobNum, String password);

    /**
     * 根据工号查询教师
     * @param JobNumber
     * @return
     */
    Teacher getTeacherUserByJobNumber(String JobNumber);

    /**
     * 分页功能
     * @param pageUtil
     * @return
     */
    PageResult getTeacherUserPage(PageUtil pageUtil);

    /**
     * 根据用户名获取用户记录
     * @param userName
     * @return
     */
    Teacher selectByTeacherName(String userName);

    /**
     * 新增用户记录
     * @param user
     * @return
     */
    int saveTeacher(Teacher user);

    /**
     * 根据主键查询用户
     * @param id
     * @return
     */
    Teacher selectByTeacherId(Long id);

    /**
     * 更改密码字段
     * @param user
     * @return
     */
    int updateTeacherPassword(Teacher user);

    /**
     * 删除用户
     * @param ids
     * @return
     */
    int deleteTeacherBatch(Integer[] ids);

    /**
     * 根据userToken获取用户记录
     *
     * @return
     */
    Teacher getTeacherUserByToken(String userToken);
}
