package com.qf.dao;

import java.sql.*;

public class JdbcDao {
    Connection con = null;
    ResultSet rs = null;

    //1、加载驱动
    static{
        try {
            //mysql5.* -- com.mysql.jdbc.Driver
            //mysql8.* -- com.mysql.cj.jdbc.Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //2、建立链接
    public Connection getConnection(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/orderdishes?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8","root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    //3、执行查询
    public ResultSet querySql(String sql,Object[] object){
        try {
            con = getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            if(object != null){
                for (int i = 0; i < object.length; i++) {
                    pstm.setObject(i+1,object[i]);
                }
            }
            rs = pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    //4、执行增删改
    public int updateSql(String sql,Object[] object){
        int num = 0; //受影响行数
        try {
            con = getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            if(object != null){
                for (int i = 0; i < object.length; i++) {
                    pstm.setObject(i+1,object[i]);
                }
            }
            num = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    //5、关闭链接
    public void close(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
