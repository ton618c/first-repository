package com.qf.bean;

import java.sql.Date;

public class Type {
    private String tid;
    private String tname;
    /*
    * java.sql.Date  年月日
    * java.util.Date 年月日时分秒
    * */
    private Date ttime;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Date getTtime() {
        return ttime;
    }

    public void setTtime(Date ttime) {
        this.ttime = ttime;
    }
}
