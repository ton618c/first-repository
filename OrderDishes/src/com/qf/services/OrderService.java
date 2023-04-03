package com.qf.services;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qf.bean.Order;
import com.qf.bean.Shop;
import com.qf.bean.Type;
import com.qf.bean.User;
import com.qf.dao.OrderDao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class OrderService {
    OrderDao orderDao = new OrderDao();

    public List<Order> selectAllOrder(String currentPageStr,String pageSizeStr) throws SQLException {
        int currentPage = Integer.valueOf(currentPageStr);
        int pageSize = Integer.valueOf(pageSizeStr);
        currentPage = (currentPage-1)*pageSize;
        return orderDao.selectAllOrder(currentPage,pageSize);
    }

    public long selectOrderCount() throws SQLException {
        return orderDao.selectOrderCount();
    }

    public void addOrder(String listJson,String tel){
        String oid = UUID.randomUUID().toString();
        int status = 1;//默认未收货
        Date ordertime = new Date(new java.util.Date().getTime());
        double money = 0;
        JSONArray sidList = JSONArray.parseArray(listJson);
        for (int i = 0; i < sidList.size(); i++) {
            String odid = UUID.randomUUID().toString();
            JSONObject job = (JSONObject) sidList.get(i);
            String sid = (String) job.get("sid");
            int priceInt = (int) job.get("price");
            double price = priceInt;
            //向订单详情表新增数据
            orderDao.addOrderDetail(odid,tel,sid,oid);
            money = money + price;
        }
        int num = orderDao.addOrder(oid,tel,ordertime,status,money);
        if(num == 0){
            throw new RuntimeException("下单失败，请检查");
        }
    }

    public List<Order> selectAllOrderByWX(String tel) throws SQLException {
        return orderDao.selectAllOrderByWX(tel);
    }
}
