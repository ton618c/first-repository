package com.qf.bean;

import java.sql.Date;

public class Order {
    private String oid;
    private User user;
    private Shop shop;
    private Type type;
    private int status;
    private Date ordertime;
    private double money;

    /***
     * list:[
     * {
     *     oid:"",
     *     user:{},
     *     shop:{},
     *     type:{},
     *     status:1
     * },
     * {}
     * ]
     */

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
