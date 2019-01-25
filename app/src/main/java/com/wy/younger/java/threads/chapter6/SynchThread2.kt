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
 *  不同的方法，但是用的是同一个锁标识
 *
 */
class SynchThread2() : Runnable {


    override fun run() {

        when {

            Thread.currentThread().name.startsWith("B") -> synchronized(SynchThread2::class.java) {
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

            Thread.currentThread().name.startsWith("C") -> synchronized(SynchThread2::class.java) {
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


        }
    }


}

fun main(args: Array<String>) {

    val synchThread = SynchThread()

    val thread1 = Thread(synchThread, "B--1")
    val thread2 = Thread(synchThread, "C--1")

    thread1.start()
    thread2.start()

    //虽然是在两个方法用了锁，但是因为是同一把锁，所以仍然不能同时执行

//    C--1_Async_Start: 11:20:33
//    B--1_Async_Start: 11:20:33
//    B--1_Async_Start: 11:20:35
//    C--1_Async_Start: 11:20:37

}