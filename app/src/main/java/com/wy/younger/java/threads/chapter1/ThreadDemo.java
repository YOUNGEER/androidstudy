package com.wy.younger.java.threads.chapter1;

/**
 * @package:com.wy.younger.threads
 * @data on:2019/1/23 13:53
 * author:YOUNG
 * desc:TODO
 */
public class ThreadDemo extends Thread {


    private String threadName = "";

    public ThreadDemo(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 5; i++) {

            System.out.println(threadName);
            try {
                sleep(600 * i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}
