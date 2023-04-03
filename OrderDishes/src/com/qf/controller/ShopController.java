package com.qf.controller;

import com.qf.bean.Shop;
import com.qf.services.ShopService;
import com.qf.utils.FileUploadUtil;
import com.qf.utils.ResultMap;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@WebServlet("/shop")
public class ShopController extends BaseController{
    ResultMap resultMap = new ResultMap();
    ShopService shopService = new ShopService();
    FileUploadUtil fileUploadUtil = new FileUploadUtil();

    public ResultMap selectAllShop(HttpServletRequest req, HttpServletResponse resp){
        try{
            String currentPage = req.getParameter("currentPage");
            String pageSize = req.getParameter("pageSize");
            List<Shop> list = shopService.selectAllShop(currentPage,pageSize);
            long total = shopService.selectShopCount();
            resultMap.setStatus(true);
            resultMap.setList(list);
            resultMap.setTotal(total);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    public ResultMap delShop(HttpServletRequest req, HttpServletResponse resp){
        try{
            String sid = req.getParameter("sid");
            String uploadPath = getServletContext().getRealPath("/") + "images";
            shopService.delShop(sid,uploadPath);
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    public ResultMap addShop(HttpServletRequest req, HttpServletResponse resp){
        try{
            //eclipse地址: 文件上传的真实地址(tomcat下运行的项目地址): D://Programe Files/tomcat8.5/webapps/FriendShip/images
            //idea地址: D:\IdeaProjects\OrderDishes\out\images
            String uploadPath = getServletContext().getRealPath("/") + "images";
            ServletFileUpload upload = fileUploadUtil.uploadConfig(uploadPath);
            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(req);
            Map<String,String> map = fileUploadUtil.formDetail(formItems, uploadPath);
            String shopJson = map.get("shopJson");
            String simage = map.get("simage");
            shopService.addShop(shopJson,simage);
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    public ResultMap editShop(HttpServletRequest req, HttpServletResponse resp){
        try{
            String sid = req.getParameter("sid");
            String sname = req.getParameter("sname");
            String price = req.getParameter("price");
            String status = req.getParameter("status");
            String tid = req.getParameter("tid");
            shopService.editShop(sid,sname,price,status,tid);
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    public ResultMap selectShopByTid(HttpServletRequest req, HttpServletResponse resp){
        try{
            String tid = req.getParameter("tid");
            List<Shop> list = shopService.selectShopByTid(tid);
            resultMap.setStatus(true);
            resultMap.setList(list);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

}
