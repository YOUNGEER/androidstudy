package com.wy.younger.java.threads.chapter2

/**
 * @package:com.wy.younger.java.threads.chapter2
 * @data on:2019/1/23 15:38
 * author:YOUNG
 * desc:TODO
 */

/**
 * 可以用 静态方法：  Thread.interrupted()和类方法 thread.isInterrupted
 * 判断是否被中断:
 *
 *thread.isInterrupted开始是false，interrupt()后会变成true，在sleep被catch后，又会重置为false
 *
 *
 * Thread.interrupted()开始是false，interrupt()后会变成true，然后再Thread.interrupted()调用时变成false，也即是
 * 一次interrupt只会得到一次Thread.interrupted为true，
 *
 * thread.isInterrupted和Thread.interrupted()一起调用时，
 * Thread.interrupted()调用为true后，thread.isInterrupted会变成false
 *
 *
 */
fun main(args: Array<String>) {
    val threadMain = Thread.currentThread()

//    println("in main ---start    " + threadMain.isInterrupted)
////    threadMain.interrupt()
//    threadMain.interrupt()
//
//    println("in main ---start    " + threadMain.isInterrupted)
//    println("in main ---start    " + threadMain.isInterrupted)
//
//    try {
//        Thread.sleep(2000)
//        println("in main ---sleep    " + threadMain.isInterrupted)
//    } catch (e: InterruptedException) {
//        println("in main ---catch    " + threadMain.isInterrupted)
//    }
//    println("in main ---end    " + threadMain.isInterrupted)

//    output
//    in main ---start    false
//    in main ---start    true
//    in main ---start    true
//    in main ---catch    false
//    in main ---end    false


//    println("in main ---start    " + Thread.interrupted())
////    threadMain.interrupt()
//    threadMain.interrupt()
//
//    println("in main ---start    " + Thread.interrupted())
//    println("in main ---start    " + Thread.interrupted())
//
//    try {
//        Thread.sleep(2000)
//        println("in main ---sleep    " + Thread.interrupted())
//    } catch (e: InterruptedException) {
//        println("in main ---catch    " + Thread.interrupted())
//    }
//    println("in main ---end    " + Thread.interrupted())


//    in main ---start    false
//    in main ---start    true
//    in main ---start    false
//    in main ---sleep    false
//    in main ---end    false


//    println("in main ---start    " + Thread.interrupted() + "     " + threadMain.isInterrupted)
////    threadMain.interrupt()
//    threadMain.interrupt()
//
//    println("in main ---start    " + Thread.interrupted() + "     " + threadMain.isInterrupted)
//    println("in main ---start    " + Thread.interrupted() + "     " + threadMain.isInterrupted)
//
//    try {
//        Thread.sleep(2000)
//        println("in main ---sleep    " + Thread.interrupted() + "     " + threadMain.isInterrupted)
//    } catch (e: InterruptedException) {
//        println("in main ---catch    " + Thread.interrupted() + "     " + threadMain.isInterrupted)
//    }
//    println("in main ---end    " + Thread.interrupted() + "     " + threadMain.isInterrupted)


//    in main ---start    false     false
//    in main ---start    true     false
//    in main ---start    false     false
//    in main ---sleep    false     false
//    in main ---end    false     false


    println("in main ---start    " + threadMain.isInterrupted + "      " + Thread.interrupted())
//    threadMain.interrupt()
    threadMain.interrupt()

    println("in main ---start    " + threadMain.isInterrupted + "      " + Thread.interrupted())
    println("in main ---start    " + threadMain.isInterrupted + "      " + Thread.interrupted())

    try {
        Thread.sleep(2000)
        println("in main ---sleep    " + threadMain.isInterrupted + "      " + Thread.interrupted())
    } catch (e: InterruptedException) {
        println("in main ---catch    " + threadMain.isInterrupted + "      " + Thread.interrupted())
    }
    println("in main ---end    " + threadMain.isInterrupted + "      " + Thread.interrupted())


//    in main ---start    false      false
//    in main ---start    true      true
//    in main ---start    false      false
//    in main ---sleep    false      false
//    in main ---end    false      false


}
