package com.wy.younger.java.threads.chapter3

/**
 *@package:com.wy.younger.java.threads.chapter3
 *@data on:2019/1/23 17:24
 *author:YOUNG
 *desc:TODO
 */
class DeprecatedSuspendResume : Any(), Runnable {

    //volatile关键字，表示该变量可能在被一个线程使用的同时，被另一个线程修改
    @Volatile
    var firstVal: Int = 0
    @Volatile
    var secondVal: Int = 0

    //判断二者是否相等
    fun areValuesEqual(): Boolean {
        return (firstVal == secondVal)
    }

    override fun run() {
        try {
            firstVal = 0
            secondVal = 0
            workMethod()
        } catch (e: InterruptedException) {
            System.out.println("interrupted while in workMethod()")
        }
    }

    fun workMethod() {
        var value = 1
        while (true) {
            stepOne(value)
            stepTwo(value)
            value++
            Thread.sleep(200)  //再次循环钱休眠200毫秒
        }
    }

    fun stepOne(value: Int) {
        firstVal = value
        Thread.sleep(300)
    }

    fun stepTwo(value: Int) {
        secondVal = value
    }

}