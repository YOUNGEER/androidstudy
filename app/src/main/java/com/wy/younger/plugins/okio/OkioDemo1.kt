package com.wy.younger.plugins.okio

import okio.Okio
import java.io.File
import java.nio.charset.Charset

/**
 *@package:com.wy.younger.plugins.okio
 *@data on:2019/1/24 16:45
 *author:YOUNG
 *desc:TODO
 */


fun main() {
    val bufferedSink = Okio.buffer(Okio.sink(File("./readme1.txt")))
    bufferedSink.writeString("this is some thing import \n", Charset.forName("utf-8"))
    bufferedSink.close()
}