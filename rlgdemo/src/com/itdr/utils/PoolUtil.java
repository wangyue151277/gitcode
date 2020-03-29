package com.itdr.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class PoolUtil {

    public static ComboPooledDataSource cpds = new ComboPooledDataSource();

    public static ComboPooledDataSource getCom(){
        return cpds;
    }

}
