package com.onlineeducation.onlineeducation.controller;


import com.onlineeducation.onlineeducation.common.Constants;
import com.onlineeducation.onlineeducation.common.Result;
import com.onlineeducation.onlineeducation.common.ResultGenerator;
import com.onlineeducation.onlineeducation.config.annotation.TokenToTeacher;
import com.onlineeducation.onlineeducation.entity.*;
import com.onlineeducation.onlineeducation.service.CourseService;
import com.onlineeducation.onlineeducation.service.TestService;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "tests/list", method = RequestMethod.GET)
    public Result listTest(@RequestParam Map<String, Object> params, @TokenToTeacher Teacher loginUser) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        if(loginUser != null)
        {
            pageUtil.put("teacherID",loginUser.getId());
        }
        //查询列表数据
        PageResult testPage = testService.getTestPage(pageUtil);
        return ResultGenerator.genSuccessResult(testPage);
    }

    @RequestMapping(value = "tests/checked", method = RequestMethod.POST)
    public Result checkTest(@RequestBody Test test) {
        if (StringUtils.isEmpty(test.getIsChecked())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        if (testService.checkTest(test) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("操作失败");
        }
    }

    @RequestMapping(value = "/tests/save", method = RequestMethod.POST)
    public Result saveTest(@RequestBody Test test, @TokenToTeacher Teacher loginUser) {
        if(loginUser == null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "未登录！");
        }
        //验证参数
        if (StringUtils.isEmpty(test.getTestName()) || StringUtils.isEmpty(test.getOptionA()) || StringUtils.isEmpty(test.getOptionB())
                || StringUtils.isEmpty(test.getOptionC()) || StringUtils.isEmpty(test.getOptionD()) || StringUtils.isEmpty(test.getAnswer())
                || StringUtils.isEmpty(test.getScore()) || StringUtils.isEmpty(test.getCourseName())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        //查询数据库 排除同名的可能
        Test tempTest = testService.selectByTestName(test.getTestName());
        if (tempTest != null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "该题目已存在勿重复添加!");
        }
        Course tempCourse = courseService.selectByCourseName(test.getCourseName());
        if(tempCourse == null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"没有此课程，请先添加课程!");
        }
        //向数据库中新增测试
        if (testService.saveTest(test,loginUser) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    @RequestMapping(value = "/tests/info/{id}", method = RequestMethod.GET)
    public Result detail(@PathVariable("id") Integer id) {
        if (id < 1) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        Test test = testService.selectByTestId(id);
        if (test == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "暂无数据！");
        } else {
            return ResultGenerator.genSuccessResult(test);
        }
    }

    @RequestMapping(value = "/tests/update", method = RequestMethod.POST)
    public Result updateTest(@RequestBody Test test) {
        //验证参数
        if (test.getId() == null || StringUtils.isEmpty(test.getTestName())||StringUtils.isEmpty(test.getOptionA())
        ||StringUtils.isEmpty(test.getOptionB())||StringUtils.isEmpty(test.getOptionC())||StringUtils.isEmpty(test.getOptionD())
        ||StringUtils.isEmpty(test.getAnswer())||StringUtils.isEmpty(test.getScore())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        //修改记录
        if (testService.updateTest(test) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    @RequestMapping(value = "tests/delete", method = RequestMethod.DELETE)
    public Result deleteTest(@RequestBody Integer[] ids) {
        if (ids.length < 1 ) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        if (testService.deleteTestBatch(ids) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
