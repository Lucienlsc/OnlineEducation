package com.onlineeducation.onlineeducation.dao;

import com.onlineeducation.onlineeducation.entity.Admin;
import com.onlineeducation.onlineeducation.entity.Student;
import com.onlineeducation.onlineeducation.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.rmi.StubNotFoundException;
import java.util.List;
import java.util.Map;

public interface StudentDao {

    /**
     * 根据用户名和密码获取学生用户
     * @param studentID
     * @param studentPassword
     * @return
     */
    Student getStudentUserByStudentIDAndPassword(@Param("studentID") String studentID, @Param("studentPassword") String studentPassword);

    /**
     * 根据学号获取学生用户
     * @param studentID
     * @return
     */
    Student getStudentUserByStudentID(String studentID);

    /**
     * 更新用户token
     * @param userId
     * @param newToken
     * @return
     */
    int updateStudentUserToken(@Param("userId") Long userId, @Param("newToken") String newToken);


    /**
     * 列表查找学生用户
     * @param param
     * @return
     */
    List<Student> findStudentUsers(Map param);

    /**
     * 获取学生用户总数
     * @param param
     * @return
     */
    int getTotalStudentUser(Map param);

    /**
     * 根据学生姓名查找学生
     * @param userName
     * @return
     */
    Student getStudentUserByUserName(String userName);

    /**
     * 添加学生用户
     * @param user
     * @return
     */
    int addStudentUser(Student user);

    /**
     * 根据id获取学生记录
     * @param id
     * @return
     */
    Student getStudentUserById(Long id);

    /**
     * 更新学生密码
     * @param userId
     * @param newPassword
     * @return
     */
    int updateStudentPassword(@Param("userId") Long userId, @Param("newPassword") String newPassword);

    /**
     * 批量删除学生用户
     * @param id
     * @return
     */
    int deleteStudentBatch(Object[] id);

    /**
     * 获取教师要分配课程的学生
     */
    List<Student> getSelectedStudents(Object[] id);

    /**
     * 根据userToken获取用户记录
     *
     * @return
     */
    Student getStudentUserByToken(String userToken);
}
