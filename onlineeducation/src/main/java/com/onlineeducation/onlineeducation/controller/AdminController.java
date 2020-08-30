package com.onlineeducation.onlineeducation.controller;

import com.onlineeducation.onlineeducation.common.Constants;
import com.onlineeducation.onlineeducation.common.Result;
import com.onlineeducation.onlineeducation.common.ResultGenerator;
import com.onlineeducation.onlineeducation.config.annotation.TokenToAdmin;
import com.onlineeducation.onlineeducation.entity.Admin;
import com.onlineeducation.onlineeducation.entity.Announcement;
import com.onlineeducation.onlineeducation.entity.Student;
import com.onlineeducation.onlineeducation.service.AdminService;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admins/login", method = RequestMethod.POST)
    public Result loginAdmin(@RequestBody Admin admin) {
        Result result = ResultGenerator.genFailResult("登录失败");
        if (StringUtils.isEmpty(admin.getAdminName()) || StringUtils.isEmpty(admin.getAdminPassword())) {
            result.setMessage("请填写登录信息!");
            return result;
        }
        Admin loginUser = adminService.login(admin.getAdminName(), admin.getAdminPassword());
        if (loginUser != null) {
            result = ResultGenerator.genSuccessResult(loginUser);
            return result;
        }
        return result;
    }







}
