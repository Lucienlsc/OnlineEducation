package com.onlineeducation.onlineeducation.controller;


import com.onlineeducation.onlineeducation.common.Constants;
import com.onlineeducation.onlineeducation.common.Result;
import com.onlineeducation.onlineeducation.common.ResultGenerator;
import com.onlineeducation.onlineeducation.config.annotation.TokenToTeacher;
import com.onlineeducation.onlineeducation.entity.Course;
import com.onlineeducation.onlineeducation.entity.Student;
import com.onlineeducation.onlineeducation.entity.Teacher;
import com.onlineeducation.onlineeducation.entity.Video;
import com.onlineeducation.onlineeducation.service.CourseService;
import com.onlineeducation.onlineeducation.service.VideoService;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/videos/list", method = RequestMethod.GET)
    public Result listVideo(@RequestParam Map<String, Object> params, @TokenToTeacher Teacher loginUser) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        if(loginUser != null)
        {
            pageUtil.put("teacherID",loginUser.getId());
        }
        //查询列表数据
        PageResult videoPage = videoService.getVideoPage(pageUtil);
        return ResultGenerator.genSuccessResult(videoPage);
    }

    @RequestMapping(value = "/videos/save", method = RequestMethod.POST)
    public Result saveVideo(@RequestBody Video video, @TokenToTeacher Teacher loginUser) {
        if(loginUser == null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "未登录！");
        }
        //验证参数
        if(StringUtils.isEmpty(video.getVideoName()) || StringUtils.isEmpty(video.getVideoURL())
                || StringUtils.isEmpty(video.getCourseName())){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"参数异常！");
        }
        //查询数据库 查找是否存在该课程
        Course tempCourse = courseService.selectByCourseName(video.getCourseName());
        if(tempCourse == null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"没有此课程，请先添加课程!");
        }
        //向数据库中新增视频
        if(videoService.saveVideo(video,loginUser) > 0){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    @RequestMapping(value = "/videos/update", method = RequestMethod.POST)
    public Result updateVideo(@RequestBody Video video) {
        if (video.getId()==null || video.getId() < 1 || StringUtils.isEmpty(video.getVideoURL())
                || StringUtils.isEmpty(video.getVideoName()) || StringUtils.isEmpty(video.getCourseName())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
//        Course tempCourse = courseService.queryById(course.getId());
//        if (tempCourse == null) {
//            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "无此记录！");
//        }
        if (videoService.updateVideo(video) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    @RequestMapping(value = "/videos/checked", method = RequestMethod.POST)
    public Result checkVideo(@RequestBody Video video) {
        if (StringUtils.isEmpty(video.getIsChecked())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        if (videoService.checkVideo(video) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("操作失败");
        }
    }

    @RequestMapping(value = "/videos/info/{id}", method = RequestMethod.GET)
    public Result detail(@PathVariable("id") Integer id) {
        if (id < 1) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        Video video = videoService.queryById(id);
        if (video == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "暂无数据！");
        } else {
            return ResultGenerator.genSuccessResult(video);
        }
    }

    @RequestMapping(value = "videos/delete", method = RequestMethod.DELETE)
    public Result deleteVideo(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        if (videoService.deleteBatch(ids) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }



}
