package com.qf.controller;

import com.alibaba.fastjson.JSON;
import com.qf.bean.User;
import com.qf.services.UserService;
import com.qf.utils.ResultMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user")
public class UserController extends BaseController {
    ResultMap resultMap = new ResultMap();
    UserService userService = new UserService();

    public ResultMap login(HttpServletRequest req, HttpServletResponse resp){
        try{
            String tel = req.getParameter("tel");
            String password = req.getParameter("password");
            User user = userService.login(tel,password);
            HttpSession session = req.getSession();
            String sessionid = session.getId();
            session.setAttribute("user",user);
            resultMap.setStatus(true);
            resultMap.setSessionid(sessionid);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    public ResultMap register(HttpServletRequest req, HttpServletResponse resp){
        try{
            String tel = req.getParameter("tel");
            String password = req.getParameter("password");
            String name = req.getParameter("name");
            String address = req.getParameter("address");
            userService.register(tel,password,name,address);
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }

        return resultMap;
    }

}
