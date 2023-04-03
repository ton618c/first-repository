package com.qf.services;

import com.alibaba.fastjson.JSON;
import com.qf.bean.Shop;
import com.qf.dao.ShopDao;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ShopService {
    ShopDao shopDao = new ShopDao();

    public List<Shop> selectAllShop(String currentPageStr,String pageSizeStr) throws SQLException {
        int currentPage = Integer.valueOf(currentPageStr);
        int pageSize = Integer.valueOf(pageSizeStr);
        currentPage = (currentPage-1)*pageSize;
        //1,5
        //2,5
        //3,5
        //select * from shop limit 0,5  01234
        //select * from shop limit 5,5  56789
        //select * from shop limit 10,5  10 11 12 13 14
        return shopDao.selectAllShop(currentPage,pageSize);
    }

    public long selectShopCount() throws SQLException {
        return shopDao.selectShopCount();
    }

    public void delShop(String sid,String uploadPath) throws SQLException {
        //删除图片
        //images/图片名
        String simage = shopDao.selectShopBySid(sid);
        //需要截取字符串，去掉images/文件夹名，保留文件名
        simage = simage.substring(simage.indexOf("/")+1);
        //D:\IdeaProjects\OrderDishes\out\images\
        String simagePath = uploadPath+ File.separator +simage;
        File file = new File(simagePath);
        if(file.exists()){
            file.delete();
        }
        //删除数据
        int num = shopDao.delShop(sid);
        if (num == 0){
            throw new RuntimeException("删除菜品失败！");
        }
    }

    public void addShop(String shopJson,String simage){
        Shop shop = JSON.parseObject(shopJson,Shop.class);
        String sid = UUID.randomUUID().toString();
        int status = 2;
        shop.setSid(sid);
        shop.setStatus(status);
        shop.setSimage(simage);
        int num = shopDao.addShop(shop);
        if (num == 0){
            throw new RuntimeException("上传菜品失败！");
        }
    }

    public void editShop(String sid,String sname,String priceStr,String statusStr,String tid){
        double price = Double.valueOf(priceStr);
        int status = Integer.valueOf(statusStr);
        String simage = "images/jdb.png";
        int num = shopDao.editShop(sid,sname,price,simage,status,tid);
        if (num == 0){
            throw new RuntimeException("修改菜品失败！");
        }
    }


    public List<Shop> selectShopByTid(String tid) throws SQLException {
        return shopDao.selectShopByTid(tid);
    }
}
