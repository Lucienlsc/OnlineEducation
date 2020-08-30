package com.onlineeducation.onlineeducation.service.impl;

import com.onlineeducation.onlineeducation.dao.AdminDao;
import com.onlineeducation.onlineeducation.dao.TeacherDao;
import com.onlineeducation.onlineeducation.entity.Admin;
import com.onlineeducation.onlineeducation.entity.Student;
import com.onlineeducation.onlineeducation.entity.Teacher;
import com.onlineeducation.onlineeducation.service.AdminService;
import com.onlineeducation.onlineeducation.service.TeacherService;
import com.onlineeducation.onlineeducation.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Teacher login(String teacherJobNum, String password) {
        //密码加密
        //调用dao方法查询用户
        Teacher teacherUser = teacherDao.getTeacherUserByTeacherJobNumAndPassword(teacherJobNum, MD5Util.MD5Encode(password, "UTF-8"));
        if (teacherUser != null) {
            //生成token
            String token = SystemUtil.genToken(System.currentTimeMillis() + "" + teacherUser.getId() + NumberUtil.genRandomNum(4));
            //更新admin表
            if (teacherDao.updateTeacherUserToken(teacherUser.getId(), token) > 0) {
                //把token给设置进去
                teacherUser.setTeacherToken(token);
                teacherUser.setId(null);
                return teacherUser;
            }
        }
        return null;
    }

    @Override
    public Teacher getTeacherUserByJobNumber(String JobNumber) {
        return teacherDao.getTeacherUserByJobNumber(JobNumber);
    }

    @Override
    public PageResult getTeacherUserPage(PageUtil pageUtil) {
        //当前页码中的数据列表
        List<Teacher> userList = teacherDao.findTeacherUsers(pageUtil);
        //获取总条数 用于计算分页数据
        int totalTeacherUser = teacherDao.getTotalTeacherUser(pageUtil);
        //封装成PageResult对象
        PageResult pageResult = new PageResult(userList, totalTeacherUser, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Teacher selectByTeacherName(String userName) {
        return teacherDao.getTeacherUserByUserName(userName);
    }

    @Override
    public int saveTeacher(Teacher user) {
        //密码加密
        user.setTeacherPassword(MD5Util.MD5Encode(user.getTeacherPassword(),"UTF-8"));

        return teacherDao.addTeacherUser(user);
    }

    @Override
    public Teacher selectByTeacherId(Long id) {
        return teacherDao.getTeacherUserById(id);
    }

    @Override
    public int updateTeacherPassword(Teacher user) {
        return teacherDao.updateTeacherPassword(user.getId(),MD5Util.MD5Encode(user.getTeacherPassword(),"UTF-8"));
    }

    @Override
    public int deleteTeacherBatch(Integer[] ids) {
        return teacherDao.deleteTeacherBatch(ids);
    }

    @Override
    public Teacher getTeacherUserByToken(String userToken) {
        return teacherDao.getTeacherUserByToken(userToken);
    }
}
