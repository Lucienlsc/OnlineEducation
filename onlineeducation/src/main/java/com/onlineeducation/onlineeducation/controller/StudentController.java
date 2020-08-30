package com.onlineeducation.onlineeducation.controller;

import com.onlineeducation.onlineeducation.common.Constants;
import com.onlineeducation.onlineeducation.common.Result;
import com.onlineeducation.onlineeducation.common.ResultGenerator;
import com.onlineeducation.onlineeducation.entity.Admin;
import com.onlineeducation.onlineeducation.entity.Student;
import com.onlineeducation.onlineeducation.entity.Teacher;
import com.onlineeducation.onlineeducation.service.AdminService;
import com.onlineeducation.onlineeducation.service.StudentService;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/students/login", method = RequestMethod.POST)
    public Result loginStudent(@RequestBody Student student) {
        Result result = ResultGenerator.genFailResult("登录失败");
        if (StringUtils.isEmpty(student.getStudentID()) || StringUtils.isEmpty(student.getStudentPassword())) {
            result.setMessage("请填写登录信息!");
            return result;
        }
        Student loginUser = studentService.login(student.getStudentID(), student.getStudentPassword());
        if (loginUser != null) {
            result = ResultGenerator.genSuccessResult(loginUser);
            return result;
        }
        return result;
    }

    @RequestMapping(value = "/students/getName/{studentID}", method = RequestMethod.GET)
    public Result getName(@PathVariable("studentID") String studentID) {
        if (studentID == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        Student student = studentService.getStudentUserByStudentID(studentID);
        if (student == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "暂无数据！");
        } else {
            return ResultGenerator.genSuccessResult(student);
        }
    }
    @RequestMapping(value = "myCourses/students/getName/{studentID}", method = RequestMethod.GET)
    public Result getNameInMyCourse(@PathVariable("studentID") String studentID) {
        if (studentID == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        Student student = studentService.getStudentUserByStudentID(studentID);
        if (student == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "暂无数据！");
        } else {
            return ResultGenerator.genSuccessResult(student);
        }
    }

    @RequestMapping(value = "/students/list", method = RequestMethod.GET)
    public Result listStudent(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        //查询列表数据
        PageResult studentUserPage = studentService.getStudentUserPage(pageUtil);
        return ResultGenerator.genSuccessResult(studentUserPage);
    }

    @RequestMapping(value = "/students/save", method = RequestMethod.POST)
    public Result saveStudent(@RequestBody Student user) {
        //验证参数
        if(StringUtils.isEmpty(user.getStudentName()) || StringUtils.isEmpty(user.getStudentPassword())
        || StringUtils.isEmpty(user.getStudentID()) || StringUtils.isEmpty(user.getStudentSex()) ||
                StringUtils.isEmpty(user.getStudentClass())){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"参数异常！");
        }
        //查询数据库 排除同名的可能
        Student tempUser = studentService.selectByStudentName(user.getStudentName());
        if(tempUser != null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"用户已存在勿重复添加!");
        }
        //向数据库中新增用户
        if(studentService.saveStudent(user) > 0){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    @RequestMapping(value = "/students/updatePassword", method = RequestMethod.POST)
    public Result updateStudentPassword(@RequestBody Student user) {
        //验证参数
        if ((user.getId() == null && user.getStudentID() == null) || StringUtils.isEmpty(user.getStudentPassword())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        //查询数据库 排除无此用户的问题
        Student tempUser1 = studentService.selectByStudentId(user.getId());
        Student tempUser2 = studentService.getStudentUserByStudentID(user.getStudentID());
        if (tempUser1 == null && tempUser2 == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "无此用户！");
        }
        if(tempUser2 != null){
            user.setId(tempUser2.getId());
        }
        //修改记录
        if (studentService.updateStudentPassword(user) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    @RequestMapping(value = "courses/detail/students/updatePassword", method = RequestMethod.POST)
    public Result updateStudentPasswordInCourseDetail(@RequestBody Student user) {
        //验证参数
        if ((user.getId() == null && user.getStudentID() == null) || StringUtils.isEmpty(user.getStudentPassword())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        //查询数据库 排除无此用户的问题
        Student tempUser1 = studentService.selectByStudentId(user.getId());
        Student tempUser2 = studentService.getStudentUserByStudentID(user.getStudentID());
        if (tempUser1 == null && tempUser2 == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "无此用户！");
        }
        if(tempUser2 != null){
            user.setId(tempUser2.getId());
        }
        //修改记录
        if (studentService.updateStudentPassword(user) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    @RequestMapping(value = "/students/delete", method = RequestMethod.DELETE)
    public Result deleteStudent(@RequestBody Integer[] ids) {
        if (ids.length < 1 ) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        if (studentService.deleteStudentBatch(ids) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
