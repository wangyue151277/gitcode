package com.itdr.testdemo;

import org.junit.Test;

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

}
