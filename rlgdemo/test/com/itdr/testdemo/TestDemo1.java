package com.itdr.testdemo;

import com.itdr.pojo.Users;
import com.itdr.utils.PoolUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDemo1 {

    @Test
    public void test1(){
        String s = "/list.do";
        String s1 = s.replace(".","/");
        System.out.println(s1);
        String[] sar = s1.split("/");
        for (int i = 0; i < sar.length; i++) {
            System.out.println(sar[i]);
        }


    }

    @Test
    public void test2() throws SQLException {

        QueryRunner qr = new QueryRunner(PoolUtil.getCom());

        String sql = "select * from users where uname = ? and upassword = ?";
        Users u = null;
        String name1 = "李四";
        String psd1 = "123";
        String name2 = "zhangsan";
        String psd2 = "111";
        u = qr.query(sql,new BeanHandler<Users>(Users.class),name2,psd2);
        System.out.println(u.toString());


    }
}
