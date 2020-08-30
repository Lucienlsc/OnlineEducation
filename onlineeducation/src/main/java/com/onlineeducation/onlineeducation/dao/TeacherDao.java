package com.onlineeducation.onlineeducation.dao;

import com.onlineeducation.onlineeducation.entity.Admin;
import com.onlineeducation.onlineeducation.entity.Courseware;
import com.onlineeducation.onlineeducation.entity.Student;
import com.onlineeducation.onlineeducation.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TeacherDao {


    /**
     * 根据用户名和密码获取教师用户
     * @param teaJobNumber
     * @param teacherPassword
     * @return
     */
    Teacher getTeacherUserByTeacherJobNumAndPassword(@Param("teaJobNumber") String teaJobNumber, @Param("teacherPassword") String teacherPassword);

    /**
     * 更新用户token
     * @param userId
     * @param newToken
     * @return
     */
    int updateTeacherUserToken(@Param("userId") Long userId, @Param("newToken") String newToken);

    /**
     * 根据工号获取教师用户
     * @param JobNumber
     * @return
     */
    Teacher getTeacherUserByJobNumber(String JobNumber);

    /**
     * 列表查找教师用户
     * @param param
     * @return
     */
    List<Teacher> findTeacherUsers(Map param);

    /**
     * 获取教师用户总数
     * @param param
     * @return
     */
    int getTotalTeacherUser(Map param);

    /**
     * 根据教师姓名查找学生
     * @param userName
     * @return
     */
    Teacher getTeacherUserByUserName(String userName);

    /**
     * 添加教师用户
     * @param user
     * @return
     */
    int addTeacherUser(Teacher user);

    /**
     * 根据id获取教师记录
     * @param id
     * @return
     */
    Teacher getTeacherUserById(Long id);

    /**
     * 更新教师密码
     * @param userId
     * @param newPassword
     * @return
     */
    int updateTeacherPassword(@Param("userId") Long userId, @Param("newPassword") String newPassword);

    /**
     * 批量删除教师用户
     * @param id
     * @return
     */
    int deleteTeacherBatch(Object[] id);

    /**
     * 根据userToken获取用户记录
     *
     * @return
     */
    Teacher getTeacherUserByToken(String userToken);


}
