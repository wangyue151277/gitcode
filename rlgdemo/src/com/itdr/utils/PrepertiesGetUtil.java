package com.itdr.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.util.Properties;

public class PrepertiesGetUtil {

    public static String getValue(String key){

        //这个工具类是为了调用配置文件里的信息并且返回io流中的值存在的;

        Properties ps = new Properties();
        //当前类调用这个类的class对象，通过这个对象调用类的加载器，通过加载器加载配置文件;
        InputStream in = PrepertiesGetUtil.
                class.getClassLoader().getResourceAsStream("const.properties");
        //从输入流中读取属性列表（键和元素对）
        try {
            ps.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //输入键，获取值，放到字符串
        String value = ps.getProperty(key);
        //返回值
        return value;

    }

    public static void main(String[] args) {
        System.out.println(getValue("USER_DISABLE_CODE"));
        System.out.println(getValue("USER_DISABLE_MSG"));
    }

}
