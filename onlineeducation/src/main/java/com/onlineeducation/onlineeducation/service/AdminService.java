package com.onlineeducation.onlineeducation.service;

import com.onlineeducation.onlineeducation.entity.Admin;
import com.onlineeducation.onlineeducation.entity.Student;
import com.onlineeducation.onlineeducation.utils.PageResult;
import com.onlineeducation.onlineeducation.utils.PageUtil;

public interface AdminService {

    /**
     * 管理员登录
     * @param userName
     * @param password
     * @return
     */
    Admin login(String userName, String password);


    /**
     * 根据userToken获取用户记录
     *
     * @return
     */
    Admin getAdminUserByToken(String userToken);


}
