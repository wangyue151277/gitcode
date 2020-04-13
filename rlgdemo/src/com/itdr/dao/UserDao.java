package com.itdr.dao;

import com.itdr.pojo.Users;
import com.itdr.utils.PoolUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    public List<Users> selectAll(String pageSize, String pageNum) {

        /*
        1.调用工具类，获取一个连接池
        2.创建dbutils的核心类QueryRunner
        3.定义一个sql语句
        4.
         */
        int pageNum1 = Integer.parseInt(pageNum);
        int pageSize1 = Integer.parseInt(pageSize);
//        ComboPooledDataSource com = PoolUtil.getCom();
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "select uname from users limit ?,?";
        List<Users> query = null;
        try {
            query = qr.query(sql,new BeanListHandler<Users>(Users.class),pageNum1,pageSize1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return query;

    }

    public Users selectOne(String username, String password) {
       
        //用dbutils的queryrunner类，的query方法
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        
        //定义一个sql
        String sql = "select * from users where uname = ? and upassword = ?";

        Users u = null;

        try {

            //转码。似乎没有用
            String uname = URLDecoder.decode(username, "utf-8");
            String upassword = URLDecoder.decode(password, "utf-8");

            u = qr.query(sql,new BeanHandler<Users>(Users.class),uname,upassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;

    }

    //根据用户名查找一个用户
    public Users selectOne(String uanme){

        QueryRunner qr = new QueryRunner(PoolUtil.getCom());

        String sql = "select * from users where uname = ?";

        Users u = null;

        try {
            u = qr.query(sql, new BeanHandler<Users>(Users.class), uanme);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    //根据用户名修改一个用户
    public int updateByUname(String  uname) {

        QueryRunner qr = new QueryRunner(PoolUtil.getCom());

        String sql = "update users set stats = 1 where uname = ?";

        int row = 0;

        try {
            row = qr.update(sql,uname);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row;


    }
}
