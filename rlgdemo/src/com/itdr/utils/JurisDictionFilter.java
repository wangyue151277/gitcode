package com.itdr.utils;

import com.itdr.common.ResponseCode;
import com.itdr.pojo.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "JurisDictionFilter",value = "/manage/*")
public class JurisDictionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        //处理乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        //统一返回对象
        ResponseCode rc = new ResponseCode();

        //转型，使用子类更多方法
        HttpServletRequest request = (HttpServletRequest)req;

        //获取路径
        String path = request.getPathInfo();

        //判断是不是在执行登陆操作，不是直接放行
        if (path.equals("/login.do")){
            chain.doFilter(req,resp);
            return;
        }

        //验证session是否有用户信息
        HttpSession session = request.getSession();
        Users u = (Users) session.getAttribute("user");

        //session没了，需要登录
        if (u == null){
            rc.setStats(3);
            rc.setMag("请登录后操作");
            //有了页面之后重定向到登录界面
            resp.getWriter().write(rc.toString());
            return;
        }

        //session判断不是管理员，退出
        if (u.getType() != 1){
            rc.setStats(3);
            rc.setMag("没有操作权限");
            resp.getWriter().write(rc.toString());
            return;
        }

        //一切没问题
        chain.doFilter(req,resp);
        return;


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
