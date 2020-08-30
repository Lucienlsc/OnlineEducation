package com.onlineeducation.onlineeducation.controller;


import com.onlineeducation.onlineeducation.common.Constants;
import com.onlineeducation.onlineeducation.common.Result;
import com.onlineeducation.onlineeducation.common.ResultGenerator;
import com.onlineeducation.onlineeducation.config.annotation.TokenToTeacher;
import com.onlineeducation.onlineeducation.entity.*;
import com.onlineeducation.onlineeducation.service.CourseService;
import com.onlineeducation.onlineeducation.service.CoursewareService;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CoursewareController {

    @Autowired
    private CoursewareService coursewareService;

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/coursewares/list", method = RequestMethod.GET)
    public Result listCourseware(@RequestParam Map<String, Object> params,@TokenToTeacher Teacher loginUser) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        if(loginUser != null)
        {
            pageUtil.put("teacherID",loginUser.getId());
        }
        //查询列表数据
        PageResult coursewarePage = coursewareService.getCoursewarePage(pageUtil);
        return ResultGenerator.genSuccessResult(coursewarePage);
    }

    @RequestMapping(value = "/coursewares/save", method = RequestMethod.POST)
    public Result saveCourseware(@RequestBody Courseware courseware, @TokenToTeacher Teacher loginUser) {
        if(loginUser == null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "未登录！");
        }
        //验证参数
        if(StringUtils.isEmpty(courseware.getCoursewareURL()) || StringUtils.isEmpty(courseware.getTitle())
                || StringUtils.isEmpty(courseware.getCourseName())){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"参数异常！");
        }
        //查询数据库 查找是否存在该课程
        Course tempCourse = courseService.selectByCourseName(courseware.getCourseName());
        if(tempCourse == null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"没有此课程，请先添加课程!");
        }
        //向数据库中新增课件
        if(coursewareService.saveCourseware(courseware,loginUser) > 0){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    @RequestMapping(value = "/coursewares/update", method = RequestMethod.POST)
    public Result updateCourseware(@RequestBody Courseware courseware) {
        if (courseware.getId()==null || courseware.getId() < 1 || StringUtils.isEmpty(courseware.getCourseName())
                || StringUtils.isEmpty(courseware.getCoursewareURL()) || StringUtils.isEmpty(courseware.getTitle())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
//        Course tempCourse = courseService.queryById(course.getId());
//        if (tempCourse == null) {
//            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "无此记录！");
//        }
        if (coursewareService.updateCourseware(courseware) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    @RequestMapping(value = "coursewares/checked", method = RequestMethod.POST)
    public Result checkCourseware(@RequestBody Courseware courseware) {
        if (StringUtils.isEmpty(courseware.getIsChecked())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        if (coursewareService.checkCourseware(courseware) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("操作失败");
        }
    }

    @RequestMapping(value = "/coursewares/info/{id}", method = RequestMethod.GET)
    public Result detail(@PathVariable("id") Integer id) {
        if (id < 1) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        Courseware courseware = coursewareService.queryById(id);
        if (courseware == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "暂无数据！");
        } else {
            return ResultGenerator.genSuccessResult(courseware);
        }
    }

    @RequestMapping(value = "coursewares/delete", method = RequestMethod.DELETE)
    public Result deleteCourseware(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        if (coursewareService.deleteBatch(ids) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
