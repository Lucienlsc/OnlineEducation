package com.onlineeducation.onlineeducation.controller;

import com.onlineeducation.onlineeducation.common.Constants;
import com.onlineeducation.onlineeducation.common.Result;
import com.onlineeducation.onlineeducation.common.ResultGenerator;
import com.onlineeducation.onlineeducation.entity.Admin;
import com.onlineeducation.onlineeducation.entity.Stu_Course;
import com.onlineeducation.onlineeducation.entity.Student;
import com.onlineeducation.onlineeducation.service.AdminService;
import com.onlineeducation.onlineeducation.service.Stu_CourseService;
import com.onlineeducation.onlineeducation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Stu_CourseController {

    @Autowired
    private Stu_CourseService stu_courseService;

    @RequestMapping(value = "/stu_course/save", method = RequestMethod.POST)
    public Result saveStu_Course(@RequestBody Stu_Course stu_course) {
        //验证参数
        if(StringUtils.isEmpty(stu_course.getCid()) || StringUtils.isEmpty(stu_course.getSid())){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"参数异常！");
        }
        //向数据库中新增记录
        if(stu_courseService.saveStu_Course(stu_course) > 0){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }



}
