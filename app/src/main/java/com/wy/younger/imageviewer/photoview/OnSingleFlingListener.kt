package com.wy.younger.imageviewer.photoview

import android.view.MotionEvent

/**
 *@package:com.wy.younger.imageviewer.photoview
 *@data on:2019/2/2 10:13
 *author:YOUNG
 *desc:单指滑动 惯性事件
 */
interface OnSingleFlingListener {


    /**
     * @param e1:MotionEvent the user first touch.
     * @param e1:MotionEvent the user last touch.
     * @param velocityX:x 速度
     * @param velocityY:y 速度
     */
    fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean

}