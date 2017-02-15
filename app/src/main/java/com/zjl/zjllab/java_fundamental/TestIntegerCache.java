package com.zjl.zjllab.java_fundamental;

/**
 * Created by zjl on 2016/12/29.
 */

public class TestIntegerCache {
    public static void main(String[] args) {
        Integer i1 = 127, i2 = 127, i3 = 128, i4 = 128;

        System.out.println("i1 == i2 is " + (i1 == i2));//true
        System.out.println("i3 == i4 is " + (i3 == i4));//false

        System.out.println("i3 == 128 is " + (i3 == 128));//true, auto unboxing
    }
}
