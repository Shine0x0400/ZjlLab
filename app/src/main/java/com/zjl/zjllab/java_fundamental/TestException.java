package com.zjl.zjllab.java_fundamental;

/**
 * Created by zjl on 2016/12/30.
 */


class Annoyance extends Exception {
}

class Sneeze extends Annoyance {
}

public class TestException {

    /**
     * Output:
     * Caught Annoyance
     * Caught Sneeze
     * Hello World!
     */
    public static void main(String[] args)
            throws Exception {
        try {
            try {
                throw new Sneeze();
            } catch (Annoyance a) {
                System.out.println("Caught Annoyance");
                throw a;
            }
        } catch (Sneeze s) {
            System.out.println("Caught Sneeze");
            return;
        } finally {
            System.out.println("Hello World!");
        }
    }
}
