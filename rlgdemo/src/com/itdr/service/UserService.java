package com.itdr.service;

import com.itdr.common.Const;
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

    //管理员登录
    public ResponseCode selectOne(String username, String password) {

        ResponseCode rc = null;

        //什么都不能少空的，空字符串也不行，默认的最前面的/前面会有一个空字符串： /manage/user/*
        //要是空的，就说有错
        if (username == null || username.equals("") || password == null || password.equals("") ){
            rc = ResponseCode.failtureRS(Const.USER_PARAMETER_CODE,Const.USER_PARAMETER_MSG);
            return rc;
        }

        //查找是否存在用户

        Users u = ud.selectOne(username, password);

        //密码或者账号错了
        if (u == null){
            rc = ResponseCode.failtureRS(Const.USER_NO_CODE,Const.USER_NO_MSG);
            return rc;
        }

        Integer type = u.getType();

        //非管理员登录
        if (type != 1){
            rc = ResponseCode.failtureRS(1,"没有登录后台的权限");
            return rc;
        }

        //管理员登录
        if (type == 1) {
            rc = ResponseCode.successRS(u);
            return rc;
        }
        return rc;

    }

    //禁用用户
    public ResponseCode selectOneAndBan(String uname){

        //创建统一返回对象
        ResponseCode rc = new ResponseCode();

        // 判断是否为空
        if (uname == null || uname.equals("")){
            rc = ResponseCode.failtureRS(Const.USER_PARAMETER_CODE,Const.USER_PARAMETER_MSG);
            return rc;
        }

        //查找是否有这个用户
        Users u = ud.selectOne(uname);

        //如果用户不存在
        if (u == null){
            rc = ResponseCode.failtureRS(Const.USER_NO_CODE,Const.USER_NO_MSG);
            return rc;
        }

        //判断用户禁用情况
        if (u.getStats() == 1){
        rc = ResponseCode.failtureRS(Const.USER_DISABLE_CODE,Const.USER_DISABLE_MSG);
        return rc;
        }

        //禁用用户失败
        int row = ud.updateByUname(uname);
        if (row <= 0){
            rc = ResponseCode.failtureRS(Const.USER_FAILURE_CODE,Const.USER_FAILURE_MSG);
            return rc;
        }

        //一切正常，正常禁用
        rc = ResponseCode.successRS(row);
        return rc;

    }



}
