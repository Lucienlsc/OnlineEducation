package com.onlineeducation.onlineeducation.service;

import com.onlineeducation.onlineeducation.entity.Admin;
import com.onlineeducation.onlineeducation.entity.Student;
import com.onlineeducation.onlineeducation.entity.Teacher;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;

import java.util.List;

public interface StudentService {

    /**
     * 学生登录
     * @param studentID
     * @param password
     * @return
     */
    Student login(String studentID, String password);

    /**
     * 根据学号查询学生
     * @param studentID
     * @return
     */
    Student getStudentUserByStudentID(String studentID);

    /**
     * 分页功能
     * @param pageUtil
     * @return
     */
    PageResult getStudentUserPage(PageUtil pageUtil);

    /**
     * 根据用户名获取学生记录
     * @param userName
     * @return
     */
    Student selectByStudentName(String userName);

    /**
     * 新增学生记录
     * @param user
     * @return
     */
    int saveStudent(Student user);

    /**
     * 根据主键查询用户
     * @param id
     * @return
     */
    Student selectByStudentId(Long id);

    /**
     * 更改密码字段
     * @param user
     * @return
     */
    int updateStudentPassword(Student user);

    /**
     * 删除用户
     * @param ids
     * @return
     */
    int deleteStudentBatch(Integer[] ids);

    /**
     * 获取教师选中的要分配课程的学生
     * @param ids
     * @return
     */
    List<Student> getSelectedStudents(Integer[] ids);

    /**
     * 根据userToken获取用户记录
     *
     * @return
     */
    Student getStudentUserByToken(String userToken);
}
