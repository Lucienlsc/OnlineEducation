package com.onlineeducation.onlineeducation.service.impl;

import com.onlineeducation.onlineeducation.dao.TestDao;
import com.onlineeducation.onlineeducation.entity.Student;
import com.onlineeducation.onlineeducation.entity.Teacher;
import com.onlineeducation.onlineeducation.entity.Test;
import com.onlineeducation.onlineeducation.service.TestService;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public PageResult getTestPage(PageUtil pageUtil) {
        if (pageUtil.get("teacherID") != null){//是教师端
            //当前页码中的数据列表
            List<Test> testList = testDao.findTestInTeacher(pageUtil);
            //获取总条数 用于计算分页数据
            int totalTestNumber = testDao.getTotalTestNumberInTeacher(pageUtil);
            //封装成PageResult对象
            PageResult pageResult = new PageResult(testList, totalTestNumber, pageUtil.getLimit(), pageUtil.getPage());
            return pageResult;
        }else{//管理员端
            //当前页码中的数据列表
            List<Test> testList = testDao.findTestInAdmin(pageUtil);
            //获取总条数 用于计算分页数据
            int totalTestNumber = testDao.getTotalTestNumberInAdmin(pageUtil);
            //封装成PageResult对象
            PageResult pageResult = new PageResult(testList, totalTestNumber, pageUtil.getLimit(), pageUtil.getPage());
            return pageResult;
        }

    }

    @Override
    public int checkTest(Test test) {
        return testDao.checkTest(test.getId(),test.getIsChecked());
    }

    @Override
    public int saveTest(Test test, Teacher teacher) {
        test.setTid(teacher.getId());
        return testDao.addTest(test);
    }

    @Override
    public Test selectByTestName(String testName) {
        return testDao.getTestByTestName(testName);
    }

    @Override
    public List<Test> selectByCourseName(String courseName) {
        return testDao.getTestByCourseName(courseName);
    }

    @Override
    public Test selectByTestId(Integer id) {
        return testDao.getTestById(id);
    }

    @Override
    public int updateTest(Test test) {
        return testDao.updateTest(test.getId(),test.getTestName(),test.getOptionA(),test.getOptionB(),test.getOptionC(),
                test.getOptionD(),test.getScore(),test.getAnswer());
    }

    @Override
    public int deleteTestBatch(Integer[] ids) {
        return testDao.deleteTestBatch(ids);
    }
}
