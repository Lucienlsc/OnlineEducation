package com.onlineeducation.onlineeducation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

//留言表
public class Message {

    private Long id;//主键

    private String content;//留言内容

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date releaseTime;//发布时间

    private String userName;//发布留言的用户姓名(包括学生和老师)

    private Long courseID;//留言对应的课程id//外键

    private Long parentID;//父级评论的id，如果不是对评论的回复，那么该值为null

    private int isDeleted;//是否已删除 0未删除 1已删除

    private Student student;//对应发布留言的学生的实体类对象

    private Course course;//对应留言课程的实体类对象

    private Teacher teacher;//对应回复留言的教师的实体类对象

    private List<Message> child;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Student getType() {
        return student;
    }

    public void setType(Student student) {
        this.student = student;
    }

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }

    public Long getParentID() {
        return parentID;
    }

    public void setParentID(Long parentID) {
        this.parentID = parentID;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Message> getChild() {
        return child;
    }

    public void setChild(List<Message> child) {
        this.child = child;
    }


    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
