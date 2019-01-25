package com.wy.younger.java.threads.chapter6

import java.text.SimpleDateFormat
import java.util.*

/**
 *@package:com.wy.younger.java.threads.chapter6
 *@data on:2019/1/24 10:34
 *author:YOUNG
 *desc:TODO
 */


/**
 * 同一个对象   不同的线程
 *
 */
class SynchThread() : Runnable {


    override fun run() {

        when {
            Thread.currentThread().name.startsWith("A") -> {
                try {
                    System.out.println(
                        Thread.currentThread().name + "_Async_Start: " + SimpleDateFormat("HH:mm:ss").format(
                            Date()
                        )
                    )
                    Thread.sleep(2000)
                    System.out.println(
                        Thread.currentThread().name + "_Async_Start: " + SimpleDateFormat("HH:mm:ss").format(
                            Date()
                        )
                    )
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }

            Thread.currentThread().name.startsWith("B") -> synchronized(this) {
                try {
                    System.out.println(
                        Thread.currentThread().name + "_Async_Start: " + SimpleDateFormat("HH:mm:ss").format(
                            Date()
                        )
                    )
                    Thread.sleep(2000)
                    System.out.println(
                        Thread.currentThread().name + "_Async_Start: " + SimpleDateFormat("HH:mm:ss").format(
                            Date()
                        )
                    )
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }

            Thread.currentThread().name.startsWith("C") -> {

                try {
                    System.out.println(
                        Thread.currentThread().name + "_Async_Start: " + SimpleDateFormat("HH:mm:ss").format(
                            Date()
                        )
                    )
                    synchronized(this) {
                        Thread.sleep(2000)
                    }
                    System.out.println(
                        Thread.currentThread().name + "_Async_Start: " + SimpleDateFormat("HH:mm:ss").format(
                            Date()
                        )
                    )
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }


            }


        }
    }


}

fun main(args: Array<String>) {

    val synchThread = SynchThread()

    //没有synchronize关键字，两个线程同时执行
//    val thread1 = Thread(synchThread, "A--1")
//    val thread2 = Thread(synchThread, "A--2")

//    A--1_Async_Start: 10:56:13
//    A--2_Async_Start: 10:56:13
//    A--1_Async_Start: 10:56:15
//    A--2_Async_Start: 10:56:15


    //方法进行了synchronize，所有线程b-1进去了后，线程b-2必须等待
//    val thread1 = Thread(synchThread, "B--1")
//    val thread2 = Thread(synchThread, "B--2")

//    B--1_Async_Start: 10:55:28
//    B--1_Async_Start: 10:55:30
//    B--2_Async_Start: 10:55:30
//    B--2_Async_Start: 10:55:32


//方法内部进行了synchronize，所有线程b-1进去了后，线程b-2也可以进去，线程b-1sleep的时候，线程b-2等待
    val thread1 = Thread(synchThread, "C--1")
    val thread2 = Thread(synchThread, "C--2")


//    C--2_Async_Start: 10:56:44
//    C--1_Async_Start: 10:56:44
//    C--2_Async_Start: 10:56:46
//    C--1_Async_Start: 10:56:48


    thread1.start()
    thread2.start()


}