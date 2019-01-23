package com.wy.younger.java.threads.chapter1;

/**
 * @package:com.wy.younger.多线程
 * @data on:2019/1/23 13:33
 * author:YOUNG
 * desc:TODO
 */
public class RunnableDemo implements Runnable {
    private Thread thread;
    private String threadName;

    public RunnableDemo(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {

            System.out.println(threadName);

            try {
                thread.sleep(600 * i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public void start() {
        if (null == thread) {
            thread = new Thread(this, threadName);
        }
        thread.start();
    }


}
