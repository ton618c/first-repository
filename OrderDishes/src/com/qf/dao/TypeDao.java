package com.qf.dao;

import com.qf.bean.Type;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeDao extends JdbcDao{

    public List<Type> selectAllType() throws SQLException {
        String sql = "select * from type";
        Object[] obj = {};
        ResultSet rs = querySql(sql,obj);
        List<Type> list = new ArrayList<Type>();
        while (rs.next()) {
            Type type = new Type();
            type.setTid(rs.getString("tid"));
            type.setTname(rs.getString("tname"));
            type.setTtime(rs.getDate("ttime"));
            list.add(type);
        }
        close();
        return list;
    }

    public int addType(String tid, String tname, Date ttime){
        String sql = "insert into type (tid,tname,ttime) value (?,?,?)";
        Object[] obj = {tid,tname,ttime};
        int num = updateSql(sql,obj);
        close();
        return num;
    }

    public int delType(String tid){
        String sql = "delete from type where tid = ?";
        Object[] obj = {tid};
        int num = updateSql(sql,obj);
        close();
        return num;
    }

    public long selectShopByTidCount(String tid) throws SQLException {
        String sql = "select count(1) as total from shop where tid = ?";
        Object[] obj = {tid};
        ResultSet rs = querySql(sql,obj);
        long total = 0;
        while (rs.next()) {
            total = rs.getLong("total");
        }
        close();
        return total;
    }

    public int editType(String tid,String tname){
        String sql = "update type set tname = ? where tid = ?";
        Object[] obj = {tname,tid};
        int num = updateSql(sql,obj);
        close();
        return num;
    }
}
