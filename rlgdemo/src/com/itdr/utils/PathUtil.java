package com.itdr.utils;

public class PathUtil {

    public static String getPath(String path){


        //把.换成/
        String s = path.replace(".","/");

        //按照/分为数组
        String[] sar = s.split("/");

        //返回数组的第二位
        return sar[1];

    }

}
