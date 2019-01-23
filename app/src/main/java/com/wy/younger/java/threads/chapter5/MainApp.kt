package com.wy.younger.java.threads.chapter5

/**
 * volatile关键字保证了数据的可见性
 *
 * 数据直接存储在主内存中，而不是cache中
 *
 * 不同线程都在主内存中进行数据的读写操作
 */
class MainApp {

    @Volatile
    var data = Data(100)


    val t1 = Thread(Runnable {
        var data1 = data
        while (data1.count > 0) {
            data1.count--
            System.out.println("thread ---1  ${data1.count}")
            Thread.sleep(100)

        }

    }, "thread-1")
    val t2 = Thread(Runnable {
        var data1 = data
        while (data1.count > 0) {
            data1.count--
            System.out.println("thread ---2  ${data1.count}")
            Thread.sleep(200)

        }
    }, "thread-2")

    fun start() {
        t1.start()
        t2.start()
    }


}

fun main(args: Array<String>) {


    val mainApp = MainApp()
    mainApp.start()


    //添加了 @Volatile
//    thread ---1  99
//    thread ---2  98
//    thread ---1  97
//    thread ---2  96
//    thread ---1  95
//    thread ---1  94
//    thread ---1  93
//    thread ---2  93
//    thread ---1  92
//    thread ---1  90
//    thread ---2  91
//    thread ---1  89
//    thread ---2  88
//    thread ---1  87
//    thread ---1  86
//    thread ---2  85
//    thread ---1  84
//    thread ---1  83
//    thread ---2  81
//    thread ---1  81
//    thread ---1  80
//    thread ---2  79
//    thread ---1  78
//    thread ---1  77
//    thread ---2  76
//    thread ---1  75
//    thread ---1  74
//    thread ---1  72
//    thread ---2  73
//    thread ---1  71
//    thread ---1  70
//    thread ---2  70
//    thread ---1  69
//    thread ---2  68
//    thread ---1  67
//    thread ---1  66
//    thread ---1  64
//    thread ---2  64
//    thread ---1  63
//    thread ---2  62
//    thread ---1  61
//    thread ---1  60
//    thread ---2  59
//    thread ---1  58
//    thread ---1  57
//    thread ---2  56
//    thread ---1  55
//    thread ---1  54
//    thread ---2  53
//    thread ---1  52
//    thread ---1  51
//    thread ---2  50
//    thread ---1  49
//    thread ---1  48
//    thread ---2  47
//    thread ---1  46
//    thread ---1  45
//    thread ---1  44
//    thread ---2  44
//    thread ---1  43
//    thread ---1  42
//    thread ---2  41
//    thread ---1  40
//    thread ---2  39
//    thread ---1  38
//    thread ---1  37
//    thread ---2  36
//    thread ---1  35
//    thread ---1  34
//    thread ---2  33
//    thread ---1  32
//    thread ---1  31
//    thread ---2  30
//    thread ---1  30
//    thread ---1  29
//    thread ---1  27
//    thread ---2  27
//    thread ---1  26
//    thread ---2  25
//    thread ---1  24
//    thread ---1  23
//    thread ---2  22
//    thread ---1  21
//    thread ---1  20
//    thread ---2  19
//    thread ---1  18
//    thread ---1  17
//    thread ---2  16
//    thread ---1  15
//    thread ---1  14
//    thread ---2  13
//    thread ---1  12
//    thread ---1  11
//    thread ---2  10
//    thread ---1  9
//    thread ---1  8
//    thread ---1  7
//    thread ---2  6
//    thread ---1  5
//    thread ---2  4
//    thread ---1  3
//    thread ---1  2
//    thread ---2  1
//    thread ---1  0


    //没有添加 @Volatile

//    thread ---1  99
//    thread ---2  98
//    thread ---1  97
//    thread ---2  96
//    thread ---1  95
//    thread ---1  94
//    thread ---1  92
//    thread ---2  92
//    thread ---1  91
//    thread ---2  90
//    thread ---1  90
//    thread ---1  89
//    thread ---1  88
//    thread ---2  88
//    thread ---1  87
//    thread ---1  86
//    thread ---2  86
//    thread ---1  85
//    thread ---1  84
//    thread ---2  84
//    thread ---1  83
//    thread ---2  82
//    thread ---1  82
//    thread ---1  81
//    thread ---2  80
//    thread ---1  80
//    thread ---1  79
//    thread ---1  78
//    thread ---2  78
//    thread ---1  77
//    thread ---2  76
//    thread ---1  76
//    thread ---1  75
//    thread ---2  74
//    thread ---1  74
//    thread ---1  73
//    thread ---1  72
//    thread ---2  72
//    thread ---1  71
//    thread ---1  70
//    thread ---2  70
//    thread ---1  69
//    thread ---1  67
//    thread ---2  67
//    thread ---1  66
//    thread ---2  65
//    thread ---1  65
//    thread ---1  64
//    thread ---1  63
//    thread ---2  63
//    thread ---1  62
//    thread ---2  61
//    thread ---1  61
//    thread ---1  60
//    thread ---2  59
//    thread ---1  59
//    thread ---1  58
//    thread ---2  56
//    thread ---1  56
//    thread ---1  55
//    thread ---1  54
//    thread ---2  54
//    thread ---1  53
//    thread ---2  52
//    thread ---1  52
//    thread ---1  51
//    thread ---1  50
//    thread ---2  50
//    thread ---1  49
//    thread ---2  48
//    thread ---1  48
//    thread ---1  47
//    thread ---2  46
//    thread ---1  46
//    thread ---1  45
//    thread ---2  44
//    thread ---1  44
//    thread ---1  43
//    thread ---2  42
//    thread ---1  42
//    thread ---1  41
//    thread ---2  40
//    thread ---1  40
//    thread ---1  39
//    thread ---1  38
//    thread ---2  38
//    thread ---1  37
//    thread ---2  35
//    thread ---1  35
//    thread ---1  34
//    thread ---1  32
//    thread ---2  32
//    thread ---1  31
//    thread ---1  30
//    thread ---2  29
//    thread ---1  28
//    thread ---2  27
//    thread ---1  27
//    thread ---1  26
//    thread ---1  25
//    thread ---2  25
//    thread ---1  24
//    thread ---1  23
//    thread ---2  23
//    thread ---1  22
//    thread ---2  21
//    thread ---1  21
//    thread ---1  20
//    thread ---2  19
//    thread ---1  18
//    thread ---1  17
//    thread ---2  16
//    thread ---1  16
//    thread ---1  15
//    thread ---2  13
//    thread ---1  13
//    thread ---1  12
//    thread ---2  11
//    thread ---1  10
//    thread ---1  9
//    thread ---1  8
//    thread ---2  8
//    thread ---1  7
//    thread ---1  6
//    thread ---2  6
//    thread ---1  5
//    thread ---1  4
//    thread ---2  4
//    thread ---1  3
//    thread ---1  2
//    thread ---2  2
//    thread ---1  1
//    thread ---1  0
}