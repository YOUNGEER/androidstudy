package com.wy.younger

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 *@package:com.wy.younger
 *@data on:2019/1/7 17:37
 *author:YOUNG
 *desc:TODO
 */
fun Context.ScreenWidht(): Int {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val outMetrics = DisplayMetrics()
    wm.defaultDisplay.getMetrics(outMetrics)
    return outMetrics.widthPixels
}

fun Context.ScreenHeight(): Int {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val outMetrics = DisplayMetrics()
    wm.defaultDisplay.getMetrics(outMetrics)
    return outMetrics.heightPixels
}