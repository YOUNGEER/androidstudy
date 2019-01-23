package com.wy.younger.java.threads.chapter1;

import java.util.concurrent.Callable;

/**
 * @package:com.wy.younger.threads
 * @data on:2019/1/23 14:03
 * author:YOUNG
 * desc:TODO
 */
public class CallbackDemo implements Callable<Integer> {

    private int start;
    private int end;


    public CallbackDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {

        int total = 0;
        for (int i = start; i <= end; i++) {
            total = total + i;
        }

        Thread.sleep(5000);

        return total;
    }
}
