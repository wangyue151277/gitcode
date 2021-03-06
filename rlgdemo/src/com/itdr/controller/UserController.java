package com.itdr.controller;

import com.itdr.common.ResponseCode;
import com.itdr.pojo.Users;
import com.itdr.service.UserService;
import com.itdr.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/manage/user/*")
public class UserController extends HttpServlet {

    //注入灵魂
    UserService us = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        注*pageNum是页码，pageSize是条数
         */

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
                break;
            case "ban":
                rc = ban(request);
                break;
        }

        //5.返回响应
        response.getWriter().write(rc.toString());

    }

    //方法之一,获取用户列表，管理员才能看
    private ResponseCode listDo(HttpServletRequest request){

        //创建统一返回对象
        ResponseCode rc = null;

        //获取登录时的session
        HttpSession session = request.getSession();

        //通过键获取user对象，里面有值
        Users user = (Users)session.getAttribute("user");

        //没登录，session没东西，就提示去登陆
        if (user == null){
            rc = ResponseCode.failtureRS(1,"请登录后操作");
            return rc;
        }

        //不是管理员，不能看，这是在登录之后才能看到的
        if (user.getType() != 1){
            rc = ResponseCode.failtureRS(1,"不是管理员没有操作权限");
            return rc;
        }

        //从前端获取的请求对象的字符串表现形式
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");

        //调用业务层的查询全部的方法,并且返回到统一返回对象
        rc = us.selectAll(pageSize, pageNum);
        return rc;

    }

    //方法之二 用户登录
    private ResponseCode loginDo(HttpServletRequest request){

        //从前端获取的请求对象的字符串表现形式
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //调用业务层的查询部分的方法,并且返回到统一返回对象
        ResponseCode rc = us.selectOne(username, password);

        //创建session对象,在用户登录成功的时候
        HttpSession session = request.getSession();
        session.setAttribute("user",rc.getData());
        System.out.println(session.getAttribute("user"));

        //返回统一返回对象
        return rc;

    }

    //方法之二 禁用用户
    private ResponseCode ban(HttpServletRequest request){

        String uname = request.getParameter("uname");

        ResponseCode rc = us.selectOneAndBan(uname);

        return rc;

    }
}