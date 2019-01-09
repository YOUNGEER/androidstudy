package com.wy.younger.utils

import android.graphics.Color

/**
 *@package:com.wy.younger.utils
 *@data on:2019/1/9 16:02
 *author:YOUNG
 *desc:TODO
 */

object ColUtils {
    fun randomRGB(): Int {

        val r: Int = (30 + Math.random() * 225).toInt()
        val g: Int = (30 + Math.random() * 225).toInt()
        val b: Int = (30 + Math.random() * 225).toInt()

        return Color.rgb(r, g, b)
    }
}