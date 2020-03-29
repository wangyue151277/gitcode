package com.itdr.service;

import com.itdr.common.ResponseCode;
import com.itdr.dao.UserDao;
import com.itdr.pojo.Users;

import java.util.List;

public class UserService {

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

        if (username == null || username.equals("")||password == null || password.equals("") ){
            rc.setStats(1);
            rc.setMag("账号密码错误");
            return rc;
        }

        //查找是否存在用户
        Users u = ud.selectOne(username, password);

        //密码或者账号错了
        if (u == null){
            rc.setStats(1);
            rc.setMag("账号密码错误");
            return rc;
        }

        //
        if (u.getType() != 1){
            rc.setStats(2);
            rc.setMag("没有操作权限");
            return rc;
        }

        rc .setStats(0);
        rc.setData(u);

        return rc;
    }
}
