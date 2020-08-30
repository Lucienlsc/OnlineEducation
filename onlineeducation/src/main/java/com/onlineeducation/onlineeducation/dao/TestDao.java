package com.onlineeducation.onlineeducation.dao;

import com.onlineeducation.onlineeducation.entity.Student;
import com.onlineeducation.onlineeducation.entity.Test;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TestDao {

    /**
     * 列表查找测试 管理员
     * @param param
     * @return
     */
    List<Test> findTestInAdmin(Map param);

    /**
     * 获取测试总数 管理员
     * @param param
     * @return
     */
    int getTotalTestNumberInAdmin(Map param);

    /**
     * 列表查找测试 教师
     * @param param
     * @return
     */
    List<Test> findTestInTeacher(Map param);

    /**
     * 获取测试总数 教师
     * @param param
     * @return
     */
    int getTotalTestNumberInTeacher(Map param);


    /**
     * 审核测试
     * @param testId
     * @param isChecked
     * @return
     */
    int checkTest(@Param("testId") Long testId, @Param("isChecked") String isChecked);

    /**
     * 添加测试
     * @param test
     * @return
     */
    int addTest(Test test);

    /**
     * 根据测试姓名查找测试
     * @param testName
     * @return
     */
    Test getTestByTestName(String testName);

    List<Test> getTestByCourseName(String courseName);


    /**
     * 根据id获取测试信息
     * @param id
     * @return
     */
    Test getTestById(Integer id);

    /**
     * 更新测试信息
     * @param tesetId
     * @param newtestName
     * @param newOptionA
     * @param newOptionB
     * @param newOptionC
     * @param newOptionD
     * @param newtestScore
     * @param newtestAnswer
     * @return
     */
    int updateTest(@Param("testId") Long tesetId, @Param("newtestName") String newtestName,
                   @Param("newOptionA") String newOptionA, @Param("newOptionB") String newOptionB
            , @Param("newOptionC") String newOptionC, @Param("newOptionD") String newOptionD
            , @Param("newtestScore") String newtestScore, @Param("newtestAnswer") String newtestAnswer);

    /**
     * 批量删除测试信息
     * @param id
     * @return
     */
    int deleteTestBatch(Object[] id);
}
