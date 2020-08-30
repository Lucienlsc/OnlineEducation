package com.onlineeducation.onlineeducation.service.impl;

import com.onlineeducation.onlineeducation.dao.AdminDao;
import com.onlineeducation.onlineeducation.dao.StudentDao;
import com.onlineeducation.onlineeducation.entity.Admin;
import com.onlineeducation.onlineeducation.entity.Student;
import com.onlineeducation.onlineeducation.entity.Teacher;
import com.onlineeducation.onlineeducation.service.AdminService;
import com.onlineeducation.onlineeducation.service.StudentService;
import com.onlineeducation.onlineeducation.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student login(String studentID, String password) {
        //密码加密
        //调用dao方法查询用户
        Student studentUser = studentDao.getStudentUserByStudentIDAndPassword(studentID, MD5Util.MD5Encode(password, "UTF-8"));
        if (studentUser != null) {
            //生成token
            String token = SystemUtil.genToken(System.currentTimeMillis() + "" + studentUser.getId() + NumberUtil.genRandomNum(4));
            //更新admin表
            if (studentDao.updateStudentUserToken(studentUser.getId(), token) > 0) {
                //把token给设置进去
                studentUser.setStudentToken(token);
                studentUser.setId(null);
                return studentUser;
            }
        }
        return null;
    }

    @Override
    public Student getStudentUserByStudentID(String studentID) {
        return studentDao.getStudentUserByStudentID(studentID);
    }

    @Override
    public PageResult getStudentUserPage(PageUtil pageUtil) {
        //当前页码中的数据列表
        List<Student> userList = studentDao.findStudentUsers(pageUtil);
        //获取总条数 用于计算分页数据
        int totalStudentUser = studentDao.getTotalStudentUser(pageUtil);
        //封装成PageResult对象
        PageResult pageResult = new PageResult(userList, totalStudentUser, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Student selectByStudentName(String userName) {
        return studentDao.getStudentUserByUserName(userName);
    }

    @Override
    public int saveStudent(Student user) {
        //密码加密
        user.setStudentPassword(MD5Util.MD5Encode(user.getStudentPassword(),"UTF-8"));
        return studentDao.addStudentUser(user);
    }

    @Override
    public Student selectByStudentId(Long id) {
        return studentDao.getStudentUserById(id);
    }

    @Override
    public int updateStudentPassword(Student user) {
        return studentDao.updateStudentPassword(user.getId(),MD5Util.MD5Encode(user.getStudentPassword(),"UTF-8"));
    }

    @Override
    public int deleteStudentBatch(Integer[] ids) {
        return studentDao.deleteStudentBatch(ids);
    }

    @Override
    public List<Student> getSelectedStudents(Integer[] ids) {
        return studentDao.getSelectedStudents(ids);
    }

    @Override
    public Student getStudentUserByToken(String userToken) {
        return studentDao.getStudentUserByToken(userToken);
    }
}
