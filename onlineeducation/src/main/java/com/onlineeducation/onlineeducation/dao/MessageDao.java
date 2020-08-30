package com.onlineeducation.onlineeducation.dao;

import com.onlineeducation.onlineeducation.entity.Announcement;
import com.onlineeducation.onlineeducation.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MessageDao {

    /**
     * 列表查找留言
     * @param param
     * @return
     */
    List<Message> findMessageInTeacher(Map param);

    /**
     * 获取留言总数
     * @param param
     * @return
     */
    int getTotalMessageNumberInTeacher(Map param);

    /**
     * 列表查找留言
     * @param param
     * @return
     */
    List<Message> findMessageInAdmin(Map param);

    /**
     * 获取留言总数
     * @param param
     * @return
     */
    int getTotalMessageNumberInAdmin(Map param);

    /**
     * 学生发留言或教师回复
     * @param message
     * @return
     */
    int replyMessage(Message message);

    /**
     * 根据id获取留言信息
     * @param id
     * @return
     */
    Message gettMessageById(Integer id);

    /**
     * 根据课程id查找属于该课程的所有留言
     * @param courseID
     * @return
     */
    List<Message> getMessageByCourseID(@Param("courseID")Integer courseID);



    /**
     * 批量删除留言
     * @param id
     * @return
     */
    int deletetMessageBatch(Object[] id);
}
