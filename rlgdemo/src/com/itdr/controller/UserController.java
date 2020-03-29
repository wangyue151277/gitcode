package com.itdr.controller;

import com.itdr.common.ResponseCode;
import com.itdr.pojo.Users;
import com.itdr.service.UserService;
import com.itdr.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/manage/user/*")
public class UserController extends HttpServlet {

    //注入灵魂
    UserService us = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }//list.do

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        注*pageNum是页码，pageSize是条数
         */

        //解决乱码问题
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text.html;charset=UTF-8");

        //获取路径
        String path = PathUtil.getPath(request.getPathInfo());

        //创建统一返回对象
        ResponseCode rc = null;

        //判断是什么请求
        switch (path){
            case "list":
                rc = listDo(request);
                break;
            case "login":
                rc = loginDo(request);
        }

        //5.返回响应
        response.getWriter().write(rc.toString());

    }



    //方法之一,获取用户列表
    private ResponseCode listDo(HttpServletRequest request){

        //从浏览器获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
        //调用业务层处理业务,并且返回
        return us.selectAll(pageSize,pageNum);

    }

    //方法之二
    private ResponseCode loginDo(HttpServletRequest request){

        //从浏览器获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //调用业务层处理业务,并且返回
        return us.selectOne(username,password);

    }
}