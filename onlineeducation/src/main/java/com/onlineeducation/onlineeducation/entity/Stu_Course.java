package com.onlineeducation.onlineeducation.entity;

//学生表
public class Stu_Course {

    private Long id;//主键

    private Integer sid;//学生id

    private Integer cid;//课程id

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}
