package com.wy.younger.imageviewer.photoview

/**
 *@package:com.wy.younger.imageviewer.photoview
 *@data on:2019/2/2 10:34
 *author:YOUNG
 *desc:TODO
 */

interface OnGestureListener {


    fun onDrag(dx: Float, dy: Float)

    fun onFling(
        startX: Float, startY: Float, velocityX: Float,
        velocityY: Float
    )

    fun onScale(scaleFactor: Float, focusX: Float, focusY: Float)

}