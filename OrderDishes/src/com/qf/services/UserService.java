package com.qf.services;

import com.qf.bean.User;
import com.qf.dao.UserDao;

import java.sql.SQLException;
import java.util.UUID;

public class UserService {
    UserDao userDao = new UserDao();

    public User login(String tel,String password) throws SQLException {
        User user = userDao.login(tel,password);
        if(user == null){
            throw new RuntimeException("账号或密码输入有误！");
        }
        return user;
    }

    public void register(String tel,String password,String name,String address){
        if(tel.equals("")){
            throw new RuntimeException("手机号必填！");
        }
        if(password.equals("")){
            throw new RuntimeException("密码必填！");
        }
        int num = userDao.register(tel,password,name,address);
        if(num == 0){
            throw new RuntimeException("注册失败！");
        }
    }
}
