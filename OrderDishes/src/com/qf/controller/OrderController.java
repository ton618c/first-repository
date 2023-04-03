package com.qf.controller;

import com.qf.bean.Order;
import com.qf.bean.Shop;
import com.qf.bean.User;
import com.qf.services.OrderService;
import com.qf.services.ShopService;
import com.qf.utils.ResultMap;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet("/order")
public class OrderController extends BaseController{
    ResultMap resultMap = new ResultMap();
    OrderService orderService = new OrderService();

    public ResultMap selectAllOrder(HttpServletRequest req, HttpServletResponse resp){
        try{
            String currentPage = req.getParameter("currentPage");
            String pageSize = req.getParameter("pageSize");
            List<Order> list = orderService.selectAllOrder(currentPage,pageSize);
            long total = orderService.selectOrderCount();
            resultMap.setStatus(true);
            resultMap.setList(list);
            resultMap.setTotal(total);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    public ResultMap addOrder(HttpServletRequest req, HttpServletResponse resp){
        try{
            String listJson = req.getParameter("listJson");
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            orderService.addOrder(listJson,user.getTel());
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    public ResultMap selectAllOrderByWX(HttpServletRequest req, HttpServletResponse resp){
        try{
            User user = (User) req.getSession().getAttribute("user");
            String tel = user.getTel();
            List<Order> list = orderService.selectAllOrderByWX(tel);
            resultMap.setStatus(true);
            resultMap.setList(list);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }
}
