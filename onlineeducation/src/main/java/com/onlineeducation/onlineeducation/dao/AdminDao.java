package com.onlineeducation.onlineeducation.dao;

import com.onlineeducation.onlineeducation.entity.*;
import com.onlineeducation.onlineeducation.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AdminDao {

    /**
     * 根据用户名和密码获取管理员用户
     * @param adminName
     * @param adminPassword
     * @return
     */
    Admin getAdminUserByUserNameAndPassword(@Param("adminName") String adminName, @Param("adminPassword") String adminPassword);

    /**
     * 更新用户token
     * @param userId
     * @param newToken
     * @return
     */
    int updateAdminUserToken(@Param("userId") Long userId, @Param("newToken") String newToken);

    /**
     * 根据userToken获取用户记录
     *
     * @return
     */
    Admin getAdminUserByToken(String userToken);



}
