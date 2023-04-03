package com.qf.controller;

import com.alibaba.fastjson.JSON;
import com.qf.utils.ResultMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setCharacterEncoding("utf-8");
            //获得每个请求的方法名
            String methodStr = req.getParameter("method");
            //this指代的不是BaseController，而是继承了BaseController的字类
            Class clazz = this.getClass();//UserController类的类型
            //从UserController中得到login方法
            Method method = clazz.getMethod(methodStr,HttpServletRequest.class,HttpServletResponse.class);
            //执行login方法
            ResultMap resultMap = (ResultMap) method.invoke(this,req,resp);
            //将对象类型转换为json字符串
            String jsonStr = JSON.toJSONString(resultMap);
            //将数据响应给前端
            resp.getWriter().print(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
