package com.onlineeducation.onlineeducation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.print.DocFlavor;
import java.util.Date;
import java.util.List;

//课件表
public class Courseware {

    private Long id;//主键

    private String title;//课件标题

    private String coursewareURL;//课件路径

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date uploadTime;//上传时间

    private Long teacher_ID;//上传课件的老师的id

    private String courseName;//课件对应的课程名称

    private int isDeleted;//是否已删除 0未删除 1已删除

    private String isChecked;//是否审核

    private Teacher teacher;//对应发布课程的教师的实体类对象

    private Course course;//对应视频的实体类对象

    private String extension;//课件后缀名

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoursewareURL() {
        return coursewareURL;
    }

    public void setCoursewareURL(String coursewareURL) {
        this.coursewareURL = coursewareURL;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Long getTeacher_ID() {
        return teacher_ID;
    }

    public void setTeacher_ID(Long teacher_ID) {
        this.teacher_ID = teacher_ID;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Course getVideo() {
        return course;
    }

    public void setVideo(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtensions(String extension) {
        this.extension = extension;
    }
}
