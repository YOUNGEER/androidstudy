package com.wy.younger.imageviewer.photoview

import android.view.View

/**
 *@package:com.wy.younger.imageviewer.photoview
 *@data on:2019/2/2 10:07
 *author:YOUNG
 *desc:TODO
 */
interface OnViewTapListener {

    /**
     *view点击回调
     *
     * @param view:view
     * @param x:坐标x
     * @param y:坐标y
     */
    fun onViewTap(view: View, x: Float, y: Float)
}