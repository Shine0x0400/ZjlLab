package com.zjl.zjllab.java_fundamental;

import java.util.concurrent.TimeUnit;

/**
 * 所有前台线程结束后会把所有daemon线程杀掉
 * Created by zjl on 2017/1/4.
 */

class DaemonTask implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("DaemonTask start");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("interrupted");
            e.printStackTrace();
        } finally {
            System.out.println("This should always run?");
        }
    }
}

public class DaemonsDontRunFinally {
    public static void main(String[] args) {
        Thread daemon = new Thread(new DaemonTask());
        daemon.setDaemon(true);
        daemon.start();

        Thread.yield();
    }
}

