package com.onlineeducation.onlineeducation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

//公告表
public class Announcement {

    private Long id;//主键

    private String title;//标题

    private String content;//内容

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date releaseTime;//发布时间

    private int isDeleted;//是否已删除 0未删除 1已删除

    private Long adminID;//发布公告的管理员的ID

    private Admin admin;//发布公告的管理员实体类对象

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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getAdminID() {
        return adminID;
    }

    public void setAdminID(Long adminID) {
        this.adminID = adminID;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
