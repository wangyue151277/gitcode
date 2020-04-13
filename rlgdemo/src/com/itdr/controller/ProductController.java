package com.itdr.controller;

import com.itdr.common.ResponseCode;
import com.itdr.service.ProductService;
import com.itdr.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductController",value = "/manage/product/*")
public class ProductController extends HttpServlet {
    //注入灵魂
    private ProductService ps = new ProductService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取路径
        String path = PathUtil.getPath(request.getPathInfo());
        //定义空的统一返回对象
        ResponseCode rc = null;

        switch (path){

        }
        //最后显示
        response.getWriter().write(rc.toString());

    }

    //查询所有商品
    private ResponseCode getAll(HttpServletRequest request){
        ResponseCode rc = ps.getAll();
        return rc;
    }
}
