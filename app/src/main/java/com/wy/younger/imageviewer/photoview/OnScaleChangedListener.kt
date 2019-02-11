package com.wy.younger.imageviewer.photoview

/**
 *@package:com.wy.younger.imageviewer.photoview
 *@data on:2019/2/2 10:19
 *author:YOUNG
 *desc:缩放监听
 */
interface OnScaleChangedListener {


    /**
     * @param scaleFactor:缩放比例系数（缩小时小于1 放大时大于1）
     * @param focusX:x 焦点
     * @param focusY:y 焦点
     */
    fun onScaleChange(scaleFactor: Float, focusX: Float, focusY: Float)

}