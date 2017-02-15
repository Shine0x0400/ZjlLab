package com.zjl.zjllab.java_fundamental;

/**
 * Created by zjl on 2016/12/29.
 */

public class TestStringCache {
    public static void main(String[] args) {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);//false
        System.out.println(s1 == s5);//true
        System.out.println(s1 == s6);//false
        System.out.println(s1 == s6.intern());//true
        System.out.println(s2 == s2.intern());//false
        System.out.println(s1 == s2.intern());//true
        System.out.println(s1 == s1.intern());//true
    }
}


