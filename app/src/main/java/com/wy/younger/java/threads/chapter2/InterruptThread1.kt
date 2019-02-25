package com.wy.younger.java.threads.chapter2

/**
 * @package:com.wy.younger.java.threads.chapter2
 * @data on:2019/1/23 15:38
 * author:YOUNG
 * desc:TODO
 */
class InterruptThread1 : Runnable {


    override fun run() {


        println("in run ---run" + System.currentTimeMillis())

        try {
            Thread.sleep(20000)

            println("in run ---sleep out" + System.currentTimeMillis())
        } catch (e: InterruptedException) {
            println("in run ---catch" + System.currentTimeMillis())
            return   //此时的return直接返回，不会走normaly out方法
        }

        println("in run ---normaly out" + System.currentTimeMillis())
    }


}

/**
 * 在sleep的时候，如果检测到interrupt，会直接抛异常
 */
fun main(args: Array<String>) {
    val interruptThread = InterruptThread1()
    val thread = Thread(interruptThread, "run" + System.currentTimeMillis())

    val threadMain = Thread()

    thread.start()
    threadMain.start()
    println("in main ---start" + System.currentTimeMillis())
    Thread.sleep(2000)
    println("in main ---sleep out" + System.currentTimeMillis())

    thread.interrupt()
    println("in main ---out" + System.currentTimeMillis())


}
