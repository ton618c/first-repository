package com.qf.dao;

import com.qf.bean.Shop;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopDao extends JdbcDao{

    public List<Shop> selectAllShop(int currentPage,int pageSize) throws SQLException {
        String sql = "select s.*,t.tname from shop as s,type as t where s.tid = t.tid limit ?,?";
        Object[] obj ={currentPage,pageSize};
        ResultSet rs = querySql(sql,obj);
        List<Shop> list = new ArrayList<Shop>();
        while (rs.next()){
            Shop shop = new Shop();
            shop.setSid(rs.getString("sid"));
            shop.setSname(rs.getString("sname"));
            shop.setSimage(rs.getString("simage"));
            shop.setTname(rs.getString("tname"));
            shop.setTid(rs.getString("tid"));
            shop.setPrice(rs.getDouble("price"));
            shop.setStatus(rs.getInt("status"));
            list.add(shop);
        }
        close();
        return list;
    }

    public long selectShopCount() throws SQLException {
        String sql = "select count(1) as total from shop as s,type as t where s.tid = t.tid";
        Object[] obj ={};
        ResultSet rs = querySql(sql,obj);
        long total = 0;
        while (rs.next()){
            total = rs.getLong("total");
        }
        close();
        return total;
    }

    public int delShop(String sid){
        String sql = "delete from shop where sid = ?";
        Object[] obj = {sid};
        int num = updateSql(sql,obj);
        close();
        return num;
    }

    public String selectShopBySid(String sid) throws SQLException {
        String sql = "select simage from shop where sid = ?";
        Object[] obj ={sid};
        ResultSet rs = querySql(sql,obj);
        String simage = "";
        while (rs.next()){
            simage = rs.getString("simage");
        }
        close();
        return simage;
    }

    public int addShop(Shop shop){
        String sql = "insert into shop (sid,sname,price,simage,status,tid) value (?,?,?,?,?,?)";
        Object[] obj = {shop.getSid(),shop.getSname(),shop.getPrice(),shop.getSimage(),shop.getStatus(),shop.getTid()};
        int num = updateSql(sql,obj);
        close();
        return num;
    }

    public int editShop(String sid,String sname,double price,String simage,int status,String tid){
        String sql = "update shop set sname=?,price=?,simage=?,status=?,tid=? where sid = ?";
        Object[] obj = {sname,price,simage,status,tid,sid};
        int num = updateSql(sql,obj);
        close();
        return num;
    }

    public List<Shop> selectShopByTid(String tid) throws SQLException {
        String sql = "select * from shop where tid = ? and status = 1";
        Object[] obj ={tid};
        ResultSet rs = querySql(sql,obj);
        List<Shop> list = new ArrayList<Shop>();
        while (rs.next()){
            Shop shop = new Shop();
            shop.setSid(rs.getString("sid"));
            shop.setSname(rs.getString("sname"));
            shop.setSimage(rs.getString("simage"));
            shop.setPrice(rs.getDouble("price"));
            shop.setStatus(rs.getInt("status"));
            list.add(shop);
        }
        close();
        return list;
    }
}
