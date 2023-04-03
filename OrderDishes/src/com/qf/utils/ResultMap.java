package com.qf.utils;

import java.util.ArrayList;
import java.util.List;

public class ResultMap<T> {
    private boolean status;
    private String message;
    private Object object;
    private List<T> list = new ArrayList<T>();
    private List<T> list1 = new ArrayList<T>();
    private long total;
    private String sessionid;

    public String getSessionid() {
        return sessionid;
    }
    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<T> getList1() {
        return list1;
    }

    public void setList1(List<T> list1) {
        this.list1 = list1;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
