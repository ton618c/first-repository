package com.qf.services;

import com.alibaba.fastjson.JSON;
import com.qf.bean.Type;
import com.qf.dao.TypeDao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class TypeService {
    TypeDao typeDao = new TypeDao();

    public List<Type> selectAllType() throws SQLException {
        return typeDao.selectAllType();
    }

    public void addType(String tname){
        String tid = UUID.randomUUID().toString();
        Date ttime = new Date(new java.util.Date().getTime());
        int num = typeDao.addType(tid,tname,ttime);
        if(num == 0){
            throw new RuntimeException("创建类别失败！");
        }
    }

    public void delType(String tid) throws SQLException {
        //查询此类别下是否有商品存在，如果有不可以删除
        long total = typeDao.selectShopByTidCount(tid);
        if(total != 0){
            throw new RuntimeException("此类别下尚有菜品，不可以删除");
        }
        int num = typeDao.delType(tid);
        if(num == 0){
            throw new RuntimeException("删除类别失败！");
        }
    }


    public void editType(String tid,String tname){
        //Type type = JSON.parseObject(typeJson,Type.class);
        int num = typeDao.editType(tid,tname);
        if(num == 0){
            throw new RuntimeException("修改类别失败！");
        }
    }
}
