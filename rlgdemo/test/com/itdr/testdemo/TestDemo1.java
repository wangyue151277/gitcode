package com.itdr.testdemo;

import com.itdr.utils.PoolUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
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

        ComboPooledDataSource com = PoolUtil.getCom();
        Connection connection = com.getConnection();
        String sql = "select * from users";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getNString(2));
        }

    }
}
