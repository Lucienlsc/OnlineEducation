package com.onlineeducation.onlineeducation.service;

import com.onlineeducation.onlineeducation.entity.*;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;

import java.util.List;

public interface MessageService {

    /**
     * 分页功能
     * @param pageUtil
     * @return
     */
    PageResult getMessagePage(PageUtil pageUtil);

    /**
     * 通过ID查找留言
     * @param id
     * @return
     */
    Message queryById(Integer id);

    /**
     * 教师回复留言
     * @param message
     * @return
     */
    int saveMessageInTeacher(Message message);

    /**
     * 学生发留言
     * @param message
     * @return
     */
    int saveMessageInStudent(Message message, Student student);

    /**
     * 根据课程id查找属于该课程的所有留言
     * @param courseID
     * @return
     */
    List<Message> queryByCourseID(Integer courseID);

    /**
     * 删除留言
     * @param ids
     * @return
     */
    int deleteMessageBatch(Integer[] ids);
}
