package com.onlineeducation.onlineeducation.service;

import com.onlineeducation.onlineeducation.entity.Course;
import com.onlineeducation.onlineeducation.entity.Student;
import com.onlineeducation.onlineeducation.entity.Teacher;
import com.onlineeducation.onlineeducation.entity.Test;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;

import java.util.List;

public interface TestService {

    /**
     * 分页功能
     * @param pageUtil
     * @return
     */
    PageResult getTestPage(PageUtil pageUtil);

    /**
     *测试
     * @param test
     * @return
     */
    int checkTest(Test test);

    /**
     * 新增测试
     * @param test
     * @return
     */
    int saveTest(Test test, Teacher teacher);

    /**
     * 根据测试名获取测试信息
     * @param testName
     * @return
     */
    Test selectByTestName(String testName);

    /**
     * 根据课程名称查找属于该课程的测试
     * @param courseName
     * @return
     */
    List<Test> selectByCourseName(String courseName);

    /**
     * 根据主键查询测试
     * @param id
     * @return
     */
    Test selectByTestId(Integer id);

    /**
     * 更改测试信息
     * @param test
     * @return
     */
    int updateTest(Test test);

    /**
     * 删除测试
     * @param ids
     * @return
     */
    int deleteTestBatch(Integer[] ids);
}
