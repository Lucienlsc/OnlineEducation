package com.onlineeducation.onlineeducation.service.impl;

import com.onlineeducation.onlineeducation.dao.AdminDao;
import com.onlineeducation.onlineeducation.entity.Admin;
import com.onlineeducation.onlineeducation.entity.Student;
import com.onlineeducation.onlineeducation.service.AdminService;
import com.onlineeducation.onlineeducation.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin login(String userName, String password) {
        //密码加密
        //调用dao方法查询用户
        Admin adminUser = adminDao.getAdminUserByUserNameAndPassword(userName, MD5Util.MD5Encode(password, "UTF-8"));
        if (adminUser != null) {
            //生成token
            String token = getNewToken(System.currentTimeMillis() + "", adminUser.getId());
            //更新admin表
            if (adminDao.updateAdminUserToken(adminUser.getId(), token) > 0) {
                //把token给设置进去
                adminUser.setAdminToken(token);
//                adminUser.setId(null);
                return adminUser;
            }
        }
        return null;
    }

    /**
     * 获取token值
     *
     * @param sessionId
     * @param userId
     * @return
     */
    private String getNewToken(String sessionId, Long userId) {
        String src = sessionId + userId + NumberUtil.genRandomNum(4);
        return SystemUtil.genToken(src);
    }

    @Override
    public Admin getAdminUserByToken(String userToken) {
        return adminDao.getAdminUserByToken(userToken);
    }



}
