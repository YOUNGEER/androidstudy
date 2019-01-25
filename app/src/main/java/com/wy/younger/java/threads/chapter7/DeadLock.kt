package com.wy.younger.java.threads.chapter7

/**
 *@package:com.wy.younger.java.threads.chapter7
 *@data on:2019/1/24 14:46
 *author:YOUNG
 *desc:TODO
 */
class DeadLock {


    @Synchronized
    fun checkOne(deadLock: DeadLock) {
        try {
            System.out.println("check one start ${Thread.currentThread().name}")
            Thread.sleep(3000)
            System.out.println("check one end ${Thread.currentThread().name}")
        } catch (e: Exception) {
            System.out.println("check one catch ${Thread.currentThread().name}")

        }
        deadLock.checkAnthor()
        System.out.println("check one out ${Thread.currentThread().name}")

    }

    @Synchronized
    fun checkAnthor() {
        System.out.println("check anthor start ${Thread.currentThread().name}")
        try {
            Thread.sleep(3000)
            System.out.println("check anthor end ${Thread.currentThread().name}")
        } catch (e: Exception) {
            System.out.println("check anthor catch ${Thread.currentThread().name}")
        }
        System.out.println("check anthor out ${Thread.currentThread().name}")
    }

}

fun main(args: Array<String>) {
    val deadLock1 = DeadLock()
    val deadLock2 = DeadLock()

    val run1 = Runnable {
        deadLock1.checkOne(deadLock2)
    }

    val run2 = Runnable {
        deadLock2.checkOne(deadLock1)
    }


    val t1 = Thread(run1, "thread-1")
    val t2 = Thread(run2, "thread-2")
    t1.start()
    t2.start()

    //线程1持有锁deadLock1   线程2持有锁deadLock2，此时线程1 获取deadLock2的锁的时候，需要等待线程2持有锁的释放
    // 线程2需要线程1的锁的时候，也需要等待线程1锁的释放


//    check one start thread-1
//    check one start thread-2
//    check one end thread-2
//    check one end thread-1

}