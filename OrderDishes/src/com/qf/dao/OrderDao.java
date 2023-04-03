package com.qf.dao;

import com.qf.bean.Order;
import com.qf.bean.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends JdbcDao{

    public List<Order> selectAllOrder(int currentPage, int pageSize) throws SQLException {
        String sql = "select o.*,u.name from orders as o,user as u where o.tel = u.tel limit ?,?";
        Object[] obj = {currentPage,pageSize};
        ResultSet rs = querySql(sql,obj);
        List<Order> list = new ArrayList<Order>();
        while (rs.next()){
            Order order = new Order();
            order.setOid(rs.getString("oid"));

            User user = new User();
            user.setName(rs.getString("name"));
            order.setUser(user);

            order.setOrdertime(rs.getDate("ordertime"));
            order.setStatus(rs.getInt("status"));
            order.setMoney(rs.getDouble("money"));
            list.add(order);
        }
        close();
        return list;
    }

    public long selectOrderCount() throws SQLException {
        String sql = "select count(1) as total from orders as o,user as u where o.tel = u.tel";
        Object[] obj = {};
        ResultSet rs = querySql(sql,obj);
        long total = 0;
        while (rs.next()){
            total = rs.getLong("total");
        }
        close();
        return total;
    }


    public int addOrderDetail(String odid,String tel,String sid,String oid){
        String sql = "insert into orderdetail (odid,tel,sid,oid) value (?,?,?,?)";
        Object[] obj = {odid,tel,sid,oid};
        int num = updateSql(sql,obj);
        close();
        return  num;
    }
    public int addOrder(String oid, String tel, Date ordertime,int status,double money){
        String sql = "insert into orders (oid,tel,ordertime,status,money) value (?,?,?,?,?)";
        Object[] obj = {oid,tel,ordertime,status,money};
        int num = updateSql(sql,obj);
        close();
        return  num;
    }

    public List<Order> selectAllOrderByWX(String tel) throws SQLException {
        String sql = "select * from orders where tel = ?";
        Object[] obj = {tel};
        ResultSet rs = querySql(sql,obj);
        List<Order> list = new ArrayList<Order>();
        while (rs.next()){
            Order order = new Order();
            order.setOid(rs.getString("oid"));
            order.setOrdertime(rs.getDate("ordertime"));
            order.setStatus(rs.getInt("status"));
            order.setMoney(rs.getDouble("money"));
            list.add(order);
        }
        close();
        return list;
    }

}
