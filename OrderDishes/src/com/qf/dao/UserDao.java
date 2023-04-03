package com.qf.dao;

import com.qf.bean.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends JdbcDao{

    public User login(String tel,String password) throws SQLException {
        String sql = "select * from user where tel = ? and password = ?";
        Object[] object = {tel,password};
        ResultSet rs = querySql(sql,object);
        User user = null;
        if(rs.next()){//如果查询结果有下一行true
            user = new User();
            user.setTel(rs.getString("tel"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setAddress(rs.getString("address"));
        }
        close();
        return user;
    }

    public int register(String tel,String password,String name,String address){
        String sql = "insert into user (tel,password,name,address) value (?,?,?,?)";
        Object[] object = {tel,password,name,address};
        int num = updateSql(sql,object);
        close();
        return num;
    }
}
