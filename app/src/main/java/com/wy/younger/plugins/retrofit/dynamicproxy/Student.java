package com.wy.younger.plugins.retrofit.dynamicproxy;


import android.util.Log;

public class Student implements Person {

    @Override
    public void doWork(String name) {
        System.out.println(name);
    }
}
