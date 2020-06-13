package com.bishop.service;

import com.bishop.dao.UserDao;
import com.bishop.domain.User;

import java.sql.SQLException;

/**
 * @ClassName UserService
 * @Author Bishop
 */
public class UserService {
    UserDao userDao = new UserDao();
    public boolean regist(User user) {
        int isRegist = 0;
        try {
            isRegist = userDao.regist(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isRegist > 0;
    }
}
