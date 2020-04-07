package com.itdr.service;

import com.itdr.common.ResponseCode;
import com.itdr.dao.UserDao;
import com.itdr.pojo.Users;

import java.util.List;

public class UserService {

    //注入灵魂
    UserDao ud = new UserDao();
    //查询全部
    public ResponseCode selectAll(String pageSize, String pageNum) {

        /*
        1.非空判断，初始化赋值
        2.创建数据层的对象，调用数据层方法，获取数据列表
        3.返回数据列表
         */

        //1
        if (pageSize == null || pageSize.equals("")){
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")){
            pageNum = "1";
        }

        //2.

        List<Users> list = ud.selectAll(pageSize,pageNum);

        //如果集合元素为null？

        ResponseCode rc = new ResponseCode();
        rc.setStats(0);
        rc.setData(list);

        //3.
        return rc;

    }

    //用户登录
    public ResponseCode selectOne(String username, String password) {

        ResponseCode rc = new ResponseCode();

        //什么都不能少空的，空字符串也不行，默认的最前面的/前面会有一个空字符串： /manage/user/*
        //要是空的，就说有错
        if (username == null || username.equals("") || password == null || password.equals("") ){
            rc.setStats(1);
            rc.setMag("账号密码错误（输入有空）");
            return rc;
        }

        //查找是否存在用户

        Users u = ud.selectOne(username, password);
        String uname = u.getUname();


        //密码或者账号错了
        if (u == null){
            rc.setStats(1);
            rc.setMag("账号密码错误（数据库没有你的账号）");
            System.out.println(u.toString());
            return rc;
        }

        Integer type = u.getType();

        //普通账号登录
        if (type == 0){
            rc.setStats(0);
            rc.setMag("普通账号登录");
            rc.setData(u);
            System.out.println(u.toString());
            return rc;
        }

        //管理员登录
        if (type == 1)
        {
            rc.setStats(0);
            rc.setMag("管理员登录成功");
            rc.setData(u);
            System.out.println(u.toString());
            return rc;
        }


        rc.setMag("其他情况");
        rc.setStats(1);
        return rc;

    }
}
