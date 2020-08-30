package com.onlineeducation.onlineeducation.entity;

//教师表
public class Teacher {

    private Long id;//主键

    private String teacherName;//教师姓名

    private String teacherSex;//教师性别

    private String teacherPassword;//教师密码

    private String teaJobNumber;//教师工号

    private int isDeleted;//是否已删除 0未删除 1已删除

    private String teacherToken;//教师token

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherSex() {
        return teacherSex;
    }

    public void setTeacherSex(String teacherSex) {
        this.teacherSex = teacherSex;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    public String getTeaJobNumber() {
        return teaJobNumber;
    }

    public void setTeaJobNumber(String teaJobNumber) {
        this.teaJobNumber = teaJobNumber;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getTeacherToken() {
        return teacherToken;
    }

    public void setTeacherToken(String teacherToken) {
        this.teacherToken = teacherToken;
    }
}
