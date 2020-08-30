package com.onlineeducation.onlineeducation.service.impl;

import com.onlineeducation.onlineeducation.dao.MessageDao;
import com.onlineeducation.onlineeducation.dao.TestDao;
import com.onlineeducation.onlineeducation.entity.Message;
import com.onlineeducation.onlineeducation.entity.Student;
import com.onlineeducation.onlineeducation.entity.Teacher;
import com.onlineeducation.onlineeducation.entity.Test;
import com.onlineeducation.onlineeducation.service.MessageService;
import com.onlineeducation.onlineeducation.service.TestService;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public PageResult getMessagePage(PageUtil pageUtil) {
        if(pageUtil.get("teacherID") != null){//是教师端，只显示该教师所有的课程下的留言
            //当前页码中的数据列表
            List<Message> MessageList = messageDao.findMessageInTeacher(pageUtil);
            //获取总条数 用于计算分页数据
            int totalMessageNumber = messageDao.getTotalMessageNumberInTeacher(pageUtil);
            //封装成PageResult对象
            PageResult pageResult = new PageResult(MessageList, totalMessageNumber, pageUtil.getLimit(), pageUtil.getPage());
            return pageResult;
        }
        else {//管理员端
            //当前页码中的数据列表
            List<Message> MessageList = messageDao.findMessageInAdmin(pageUtil);
            //获取总条数 用于计算分页数据
            int totalMessageNumber = messageDao.getTotalMessageNumberInAdmin(pageUtil);
            //封装成PageResult对象
            PageResult pageResult = new PageResult(MessageList, totalMessageNumber, pageUtil.getLimit(), pageUtil.getPage());
            return pageResult;
        }

    }

    @Override
    public Message queryById(Integer id) {
        return messageDao.gettMessageById(id);
    }

    @Override
    public int saveMessageInTeacher(Message message) {
        return messageDao.replyMessage(message);
    }

    @Override
    public int saveMessageInStudent(Message message, Student student) {
        if(message.getParentID()==null){
            message.setParentID(null);
        }
        return messageDao.replyMessage(message);
    }

    @Override
    public List<Message> queryByCourseID(Integer courseID) {
        return messageDao.getMessageByCourseID(courseID);
    }


    @Override
    public int deleteMessageBatch(Integer[] ids) {
        return messageDao.deletetMessageBatch(ids);
    }
}
