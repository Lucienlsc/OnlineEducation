package com.onlineeducation.onlineeducation.controller;


import com.onlineeducation.onlineeducation.common.Constants;
import com.onlineeducation.onlineeducation.common.Result;
import com.onlineeducation.onlineeducation.common.ResultGenerator;
import com.onlineeducation.onlineeducation.config.annotation.TokenToAdmin;
import com.onlineeducation.onlineeducation.entity.Admin;
import com.onlineeducation.onlineeducation.entity.Announcement;
import com.onlineeducation.onlineeducation.entity.Student;
import com.onlineeducation.onlineeducation.entity.Test;
import com.onlineeducation.onlineeducation.service.AnnouncementService;
import com.onlineeducation.onlineeducation.service.TestService;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @RequestMapping(value = "/announcements/list", method = RequestMethod.GET)
    public Result listAnnouncement(@RequestParam Map<String, Object> params ) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        //查询列表数据
        PageResult announcementPage = announcementService.getAnnouncementPage(pageUtil);
        return ResultGenerator.genSuccessResult(announcementPage);
    }

    @RequestMapping(value = "/announcements/save", method = RequestMethod.POST)
    public Result saveAnnouncement(@RequestBody Announcement announcement, @TokenToAdmin Admin loginUser) {
        if (loginUser == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "未登录！");
        }
        //验证参数
        if (StringUtils.isEmpty(announcement.getContent()) || StringUtils.isEmpty(announcement.getTitle())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        //查询数据库 排除同名的可能
        Announcement tempAnnouncement = announcementService.selectByAnnouncementTitle(announcement.getTitle());
        if (tempAnnouncement != null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "该公告已存在勿重复添加!");
        }
        //向数据库中新增公告
        if (announcementService.saveAnnouncement(announcement,loginUser) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    //数据回显
    @RequestMapping(value = "/announcements/info/{id}", method = RequestMethod.GET)
    public Result info(@PathVariable("id") Long id) {
        Announcement announcement = announcementService.selectByAnnouncementId(id);
        return ResultGenerator.genSuccessResult(announcement);
    }

    @RequestMapping(value = "/announcements/updateAnnouncement", method = RequestMethod.POST)
    public Result updateStudentPassword(@RequestBody Announcement announcement,@TokenToAdmin Admin loginUser) {
        //验证参数
        if (announcement.getId() == null || StringUtils.isEmpty(announcement.getTitle()) || StringUtils.isEmpty(announcement.getContent())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        if (loginUser == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "未登录！");
        }
        //查询数据库 排除无此公告的问题
        Announcement temp = announcementService.selectByAnnouncementId(announcement.getId());
        if (temp == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "无此公告！");
        }
        //修改记录
        if (announcementService.updateAnnouncement(announcement,loginUser) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    @RequestMapping(value = "announcements/delete", method = RequestMethod.DELETE)
    public Result deleteAnnouncement(@RequestBody Integer[] ids) {
        if (ids.length < 1 ) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        if (announcementService.deleteAnnouncementBatch(ids) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
