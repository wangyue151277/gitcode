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
import javax.servlet.http.HttpSession;
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

        //解决乱码问题
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

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
        System.out.println(rc.toString());

    }

    //方法之一,获取用户列表，管理员才能看
    private ResponseCode listDo(HttpServletRequest request){

        //创建统一返回对象
        ResponseCode rs = new ResponseCode();

        //获取登录时的session
        HttpSession session = request.getSession();

        //通过键获取user对象，里面有值
        Users user = (Users)session.getAttribute("user");

        //没登录，session没东西，就提示去登陆
        if (user == null){
            rs.setStats(3);
            rs.setMag("请登录后操作");
            return rs;
        }

        //不是管理员，不能看，这是在登录之后才能看到的
        if (user.getType() != 1){
            rs.setStats(3);
            rs.setMag("没有操作权限");
            return rs;
        }

        //从前端获取的请求对象的字符串表现形式
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");

        //调用业务层的查询全部的方法,并且返回到统一返回对象
        rs = us.selectAll(pageSize, pageNum);
        return rs;

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

        //返回统一返回对象
        return rc;

    }
}