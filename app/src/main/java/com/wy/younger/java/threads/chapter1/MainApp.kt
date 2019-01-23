package com.wy.younger.java.threads.chapter1

import java.util.concurrent.FutureTask

/**
 *@package:com.wy.younger.多线程
 *@data on:2019/1/23 13:45
 *author:YOUNG
 *desc:TODO
 */


fun main(args: Array<String>) {


    //runnable start
//    val thread1 = RunnableDemo("thread---1")
//    val thread2 = RunnableDemo("thread-------2")
//
//    thread1.start()
//    thread2.start()


    //thread start
//    val thread1 = ThreadDemo("thread---1")
//    val thread2 = ThreadDemo("thread-------2")
//    thread1.start()
//    thread2.start()


    //callable start
    val callbackDemo1 = CallbackDemo(0, 4999)
    val callbackDemo2 = CallbackDemo(5000, 9999)
    val futureTask = FutureTask<Int>(callbackDemo1)
    val thread = Thread(futureTask, "thread22")
    thread.start()

}