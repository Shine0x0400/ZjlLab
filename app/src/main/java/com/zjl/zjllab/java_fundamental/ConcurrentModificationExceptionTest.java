package com.zjl.zjllab.java_fundamental;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zjl on 2017/3/8.
 */

public class ConcurrentModificationExceptionTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            list.add("" + i);
        }

        for (String item : list) {
            if ("6".equals(item)) {
                list.remove(item);
                System.out.println("remove:" + item);
//                return;
            }
        }

        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String item = iter.next();
            System.out.println(item);

            if ("6".equals(item)) {
                iter.remove();
                System.out.println("remove:" + item);
            }
        }

        System.out.println("================");
        for (String item : list) {
            System.out.println(item);
        }
    }
}
