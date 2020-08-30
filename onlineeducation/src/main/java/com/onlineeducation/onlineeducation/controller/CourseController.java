package com.onlineeducation.onlineeducation.controller;


import com.onlineeducation.onlineeducation.common.Constants;
import com.onlineeducation.onlineeducation.common.Result;
import com.onlineeducation.onlineeducation.common.ResultGenerator;
import com.onlineeducation.onlineeducation.config.annotation.TokenToTeacher;
import com.onlineeducation.onlineeducation.entity.*;
import com.onlineeducation.onlineeducation.service.*;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private CoursewareService coursewareService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private TestService testService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;


    @GetMapping("/courses/list")
    @ResponseBody
    public Result listCourse(@RequestParam Map<String, Object> params, @TokenToTeacher Teacher loginUser) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }

        PageUtil pageUtil = new PageUtil(params);
        if(loginUser != null)
        {
            pageUtil.put("teacherID",loginUser.getId());
        }
        //查询列表数据
        PageResult coursePage = courseService.getCoursePage(pageUtil);
        return ResultGenerator.genSuccessResult(coursePage);
    }

    @PostMapping("/courses/save")
    @ResponseBody
    public Result saveCourse(@RequestBody Course course, @TokenToTeacher Teacher loginUser) {
        if(loginUser == null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "未登录！");
        }
        //验证参数
        if(StringUtils.isEmpty(course.getCourseName()) || StringUtils.isEmpty(course.getDescription())
                || StringUtils.isEmpty(course.getImageURL())){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"参数异常！");
        }
        //向数据库中新增课程
        if(courseService.saveCourse(course,loginUser) > 0){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    @GetMapping("/courses/info/{id}")
    @ResponseBody
    public Result detail(@PathVariable("id") Integer id) {
        if (id < 1) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        Course course = courseService.queryById(id);
        if (course == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "暂无数据！");
        } else {
            return ResultGenerator.genSuccessResult(course);
        }
    }

    @PostMapping( "/courses/update")
    @ResponseBody
    public Result updateCourse(@RequestBody Course course) {
        if (course.getId()==null || course.getId() < 1 || StringUtils.isEmpty(course.getImageURL())
                || StringUtils.isEmpty(course.getCourseName()) || StringUtils.isEmpty(course.getDescription())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
//        Course tempCourse = courseService.queryById(course.getId());
//        if (tempCourse == null) {
//            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "无此记录！");
//        }
        if (courseService.updateCourse(course) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    @PostMapping("courses/checked")
    @ResponseBody
    public Result checkCourse(@RequestBody Course course) {
        if (StringUtils.isEmpty(course.getIsChecked())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        if (courseService.checkCourse(course) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("操作失败");
        }
    }

    @DeleteMapping("courses/delete")
    @ResponseBody
    public Result deleteCourse(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        if (courseService.deleteBatch(ids) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }

    @GetMapping("teacher/courses/getTeacherCourses")
    @ResponseBody
    public Result getTeacherCourses(HttpServletRequest request,@TokenToTeacher Teacher loginTeacher){
        if (loginTeacher == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "未登录！");
        }
        List<Course> teacherCourses = courseService.getTeacherCourseList(loginTeacher.getId());
        request.setAttribute("teacherCourses",teacherCourses);
        return ResultGenerator.genSuccessResult(teacherCourses);
    }

    @GetMapping("teacher/teacherStudent.html")
    public String getTeacherCoursesInThymeleaf(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                Teacher loginTeacher = teacherService.getTeacherUserByToken(token);
                Long tid = loginTeacher.getId();
                List<Course> teacherCourses = courseService.getTeacherCourseList(tid);
                request.setAttribute("teacherCourses",teacherCourses);
                break;
            }
        }
        return "teacher/teacherStudent";
    }


//    学生端
    @GetMapping("/index-login.html")
    public String listStudentCourse(HttpServletRequest request) {
        List<Course> courses = courseService.getCourseList();
        request.setAttribute("courses",courses);
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                Student loginStudent = studentService.getStudentUserByToken(token);
                Long sid = loginStudent.getId();
                request.setAttribute("sid",sid);
                break;
            }else{
                request.setAttribute("sid",null);
            }
        }
        return "index-login";
    }

    @GetMapping("/index.html")
    public String listStudentCourseWithoutLogin(HttpServletRequest request) {
        List<Course> courses = courseService.getCourseList();
        request.setAttribute("courses",courses);

        return "index";
    }


    @GetMapping("/myCourses/{sid}.html")
    public String listMyCourse(@PathVariable("sid")Integer sid,HttpServletRequest request){

        List<Course> myCourses = courseService.getMyCourseList(sid);
        if(myCourses.size() > 0){
            request.setAttribute("myCourses",myCourses);
        }else{
            request.setAttribute("myCourses",null);
        }

        return "myCourse";
    }

    @GetMapping("/courses/detail/{id}.html")
    public String detailCourse(@PathVariable("id")Integer id,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                Student loginStudent = studentService.getStudentUserByToken(token);
                if(loginStudent != null){
                    Long sid = loginStudent.getId();
                    request.setAttribute("sid",sid);
                    break;
                }else{
                    break;
                }

            }else{
                request.setAttribute("sid",null);
            }
        }
        Course course = courseService.queryById(id);
        String courseName = course.getCourseName();
        List<Video> courseVideos = videoService.selectVideoByCourseName(courseName);
        List<Courseware> coursewares = coursewareService.selectCoursewareByCourseName(courseName);
        List<String> fileNames = new ArrayList<>();
        for(int i = 0; i < coursewares.size(); i++) {
            fileNames.add(coursewares.get(i).getCoursewareURL());
        }
        for(int j = 0; j < coursewares.size(); j++){
            coursewares.get(j).setExtensions((fileNames.get(j).substring(fileNames.get(j).lastIndexOf("."))));
        }
        List<Test> tests = testService.selectByCourseName(courseName);
        request.setAttribute("course",course);
        if(courseVideos.size() > 0){
            Video firstVideo = courseVideos.get(0);
            request.setAttribute("firstVideo",firstVideo);
            request.setAttribute("courseVideos",courseVideos);
        }else{
            request.setAttribute("firstVideo",null);
            request.setAttribute("courseVideos",null);
        }
        if(coursewares.size() > 0){
            request.setAttribute("coursewares",coursewares);
        }else{
            request.setAttribute("coursewares",null);
        }
        if(tests.size() > 0){
            request.setAttribute("tests",tests);
        }else{
            request.setAttribute("tests",null);
        }

        /**
         * 根据课程id查询该课程下的所有留言
         */
        List<Message> allMessages = messageService.queryByCourseID(id);
        if(allMessages == null || allMessages.size() == 0){
            request.setAttribute("messages",null);
//            return null;
        }else{
            List<Message> messages = new ArrayList<>();
            List<Message> parents = new ArrayList<>();
            for(Message message : allMessages){
                if (message.getParentID() == null){
                    messages.add(message);
                    parents.add(message);
                }else{
                    boolean foundParent = false;
                    for(Message parent : parents){
                        if (message.getParentID() == parent.getId()){
                            if(parent.getChild() == null){
                                parent.setChild(new ArrayList<>());
                            }
                            parent.getChild().add(message);
                            parents.add(message);
                            foundParent = true;
                            break;
                        }
                    }
                    if(!foundParent){
                        throw new RuntimeException("can not find the parent message");
                    }
                }
            }
            request.setAttribute("messages",messages);
        }
        return "detail";
    }

    @GetMapping("/courses/detail/videoPlay/{courseID}/{videoID}.html")
    public String videoPlay(@PathVariable("courseID")Integer courseID,@PathVariable("videoID")Integer videoID,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                Student loginStudent = studentService.getStudentUserByToken(token);
                Long sid = loginStudent.getId();
                request.setAttribute("sid",sid);
                break;
            }else{
                request.setAttribute("sid",null);
            }
        }
        Video video = videoService.getVideoById(videoID);
        request.setAttribute("video",video);
        Course course = courseService.queryById(courseID);
        String courseName = course.getCourseName();
        List<Video> courseVideos = videoService.selectVideoByCourseName(courseName);
        List<Courseware> coursewares = coursewareService.selectCoursewareByCourseName(courseName);
        List<String> fileNames = new ArrayList<>();
        for(int i = 0; i < coursewares.size(); i++) {
            fileNames.add(coursewares.get(i).getCoursewareURL());
        }
        for(int j = 0; j < coursewares.size(); j++){
            coursewares.get(j).setExtensions((fileNames.get(j).substring(fileNames.get(j).lastIndexOf("."))));
        }
        List<Test> tests = testService.selectByCourseName(courseName);
        request.setAttribute("course",course);
        if(courseVideos.size() > 0){
            Video firstVideo = courseVideos.get(0);
            request.setAttribute("firstVideo",firstVideo);
            request.setAttribute("courseVideos",courseVideos);
        }else{
            request.setAttribute("firstVideo",null);
            request.setAttribute("courseVideos",null);
        }
        if(coursewares.size() > 0){
            request.setAttribute("coursewares",coursewares);
        }else{
            request.setAttribute("coursewares",null);
        }
        if(tests.size() > 0){
            request.setAttribute("tests",tests);
        }else{
            request.setAttribute("tests",null);
        }

        /**
         * 根据课程id查询该课程下的所有留言
         */
        List<Message> allMessages = messageService.queryByCourseID(courseID);
        if(allMessages == null || allMessages.size() == 0){
            request.setAttribute("messages",null);
//            return null;
        }else{
            List<Message> messages = new ArrayList<>();
            List<Message> parents = new ArrayList<>();
            for(Message message : allMessages){
                if (message.getParentID() == null){
                    messages.add(message);
                    parents.add(message);
                }else{
                    boolean foundParent = false;
                    for(Message parent : parents){
                        if (message.getParentID() == parent.getId()){
                            if(parent.getChild() == null){
                                parent.setChild(new ArrayList<>());
                            }
                            parent.getChild().add(message);
                            parents.add(message);
                            foundParent = true;
                            break;
                        }
                    }
                    if(!foundParent){
                        throw new RuntimeException("can not find the parent message");
                    }
                }
            }
            request.setAttribute("messages",messages);
        }

        return "video";
    }
}
