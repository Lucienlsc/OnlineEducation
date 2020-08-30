package com.onlineeducation.onlineeducation.controller;


import com.onlineeducation.onlineeducation.common.Constants;
import com.onlineeducation.onlineeducation.common.Result;
import com.onlineeducation.onlineeducation.common.ResultGenerator;
import com.onlineeducation.onlineeducation.config.annotation.TokenToAdmin;
import com.onlineeducation.onlineeducation.config.annotation.TokenToStudent;
import com.onlineeducation.onlineeducation.config.annotation.TokenToTeacher;
import com.onlineeducation.onlineeducation.entity.*;
import com.onlineeducation.onlineeducation.service.MessageService;
import com.onlineeducation.onlineeducation.service.TestService;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/messages/list", method = RequestMethod.GET)
    public Result listMessage(@RequestParam Map<String, Object> params, @TokenToTeacher Teacher loginTeacher, @TokenToAdmin Admin loginAdmin) {
        if(loginTeacher == null && loginAdmin == null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "未登录！");
        }
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        if(loginTeacher != null)
        {
            pageUtil.put("teacherID",loginTeacher.getId());
        }
        //查询列表数据
        PageResult messagePage = messageService.getMessagePage(pageUtil);
        return ResultGenerator.genSuccessResult(messagePage);
    }

    @RequestMapping(value = "/messages/info/{id}", method = RequestMethod.GET)
    public Result detail(@PathVariable("id") Integer id) {
        if (id < 1) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        Message message = messageService.queryById(id);
        if (message == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "暂无数据！");
        } else {
            return ResultGenerator.genSuccessResult(message);
        }
    }

    @RequestMapping(value = "courses/detail/messages/save",method = RequestMethod.POST)
    public Result saveMessage(@RequestBody Message message, @TokenToTeacher Teacher teacherUser, @TokenToStudent Student studentUser) {
        if(teacherUser == null && studentUser == null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "未登录！");
        }
        //验证参数
        if (StringUtils.isEmpty(message.getContent()) || StringUtils.isEmpty(message.getCourseID()) || StringUtils.isEmpty(message.getUserName())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        //向数据库中新增留言
        if(teacherUser != null){
            if (messageService.saveMessageInTeacher(message) > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("发送失败");
            }
        }else {
            if (messageService.saveMessageInStudent(message,studentUser) > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("发送失败");
            }
        }
    }

    @RequestMapping(value = "messages/reply",method = RequestMethod.POST)
    public Result replyMessage(@RequestBody Message message, @TokenToTeacher Teacher teacherUser) {
        if(teacherUser == null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "未登录！");
        }
        //验证参数
        if (StringUtils.isEmpty(message.getContent()) || StringUtils.isEmpty(message.getCourseID())
                || StringUtils.isEmpty(message.getUserName()) || StringUtils.isEmpty(message.getParentID())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        //向数据库中新增留言
        if (messageService.saveMessageInTeacher(message) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("发送失败");
        }

    }

    @RequestMapping(value = "/messages/delete", method = RequestMethod.DELETE)
    public Result deleteMessage(@RequestBody Integer[] ids) {
        if (ids.length < 1 ) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        if (messageService.deleteMessageBatch(ids) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }

}
