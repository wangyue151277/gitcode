package com.itdr.dao;

import com.itdr.pojo.Users;
import com.itdr.utils.PoolUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
//      ComboPooledDataSource com = PoolUtil.getCom();
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "select * from users where uname = ? and upassword = ?";
        Users u = null;
        try {
            u = qr.query(sql,new BeanHandler<Users>(Users.class),username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;

    }
}
