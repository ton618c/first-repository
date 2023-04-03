package com.qf.controller;

import com.qf.bean.Type;
import com.qf.services.TypeService;
import com.qf.utils.ResultMap;

import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.List;

@WebServlet("/type")
public class TypeController extends BaseController{
    ResultMap resultMap = new ResultMap();
    TypeService typeService = new TypeService();


    public ResultMap selectAllType(HttpServletRequest req, HttpServletResponse resp){
        try {
            List<Type> list = typeService.selectAllType();
            resultMap.setStatus(true);
            resultMap.setList1(list);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    public ResultMap addType(HttpServletRequest req, HttpServletResponse resp){
        try {
            String tname = req.getParameter("tname");
            typeService.addType(tname);
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    public ResultMap delType(HttpServletRequest req, HttpServletResponse resp){
        try {
            String tid = req.getParameter("tid");
            typeService.delType(tid);
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    public ResultMap editType(HttpServletRequest req, HttpServletResponse resp){
        try {
            String tid = req.getParameter("tid");
            String tname = req.getParameter("tname");
            typeService.editType(tid,tname);
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

}
