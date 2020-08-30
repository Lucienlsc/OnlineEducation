package com.onlineeducation.onlineeducation.controller;

import com.onlineeducation.onlineeducation.common.Constants;
import com.onlineeducation.onlineeducation.common.Result;
import com.onlineeducation.onlineeducation.common.ResultGenerator;
import com.onlineeducation.onlineeducation.entity.Admin;
import com.onlineeducation.onlineeducation.entity.Student;
import com.onlineeducation.onlineeducation.entity.Teacher;
import com.onlineeducation.onlineeducation.service.AdminService;
import com.onlineeducation.onlineeducation.service.TeacherService;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/teachers/login", method = RequestMethod.POST)
    public Result loginTeacher(@RequestBody Teacher teacher) {
        Result result = ResultGenerator.genFailResult("登录失败");
        if (StringUtils.isEmpty(teacher.getTeaJobNumber()) || StringUtils.isEmpty(teacher.getTeacherPassword())) {
            result.setMessage("请填写登录信息!");
            return result;
        }
        Teacher loginUser = teacherService.login(teacher.getTeaJobNumber(), teacher.getTeacherPassword());
        if (loginUser != null) {
            result = ResultGenerator.genSuccessResult(loginUser);
            return result;
        }
        return result;
    }

    @RequestMapping(value = "/teachers/getName/{JobNumber}", method = RequestMethod.GET)
    public Result getName(@PathVariable("JobNumber") String JobNumber) {
        if (JobNumber == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        Teacher teacher = teacherService.getTeacherUserByJobNumber(JobNumber);
        if (teacher == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "暂无数据！");
        } else {
            return ResultGenerator.genSuccessResult(teacher);
        }
    }

    @RequestMapping(value = "/teachers/list", method = RequestMethod.GET)
    public Result listTeacher(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        //查询列表数据
        PageResult teacherUserPage = teacherService.getTeacherUserPage(pageUtil);
        return ResultGenerator.genSuccessResult(teacherUserPage);
    }

    @RequestMapping(value = "/teachers/save", method = RequestMethod.POST)
    public Result saveTeacher(@RequestBody Teacher user) {
        //验证参数
        if(StringUtils.isEmpty(user.getTeacherName()) || StringUtils.isEmpty(user.getTeacherPassword()) ||
                StringUtils.isEmpty(user.getTeaJobNumber()) || StringUtils.isEmpty(user.getTeacherSex())){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"参数异常！");
        }
        //查询数据库 排除同名的可能
        Teacher tempUser = teacherService.selectByTeacherName(user.getTeacherName());
        if(tempUser != null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"用户已存在勿重复添加!");
        }
        //向数据库中新增用户
        if(teacherService.saveTeacher(user) > 0){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    @RequestMapping(value = "/teachers/updatePassword", method = RequestMethod.POST)
    public Result updateTeacherPassword(@RequestBody Teacher user) {
        //验证参数
        if (user.getId() == null || StringUtils.isEmpty(user.getTeacherPassword())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        //查询数据库 排除无此用户的问题
        Teacher tempUser = teacherService.selectByTeacherId(user.getId());
        if (tempUser == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "无此用户！");
        }
        //修改记录
        if (teacherService.updateTeacherPassword(user) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    @RequestMapping(value = "/teachers/delete", method = RequestMethod.DELETE)
    public Result deleteTeacher(@RequestBody Integer[] ids) {
        if (ids.length < 1 ) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        if (teacherService.deleteTeacherBatch(ids) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
