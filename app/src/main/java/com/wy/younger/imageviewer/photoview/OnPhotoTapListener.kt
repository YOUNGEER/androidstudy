package com.wy.younger.imageviewer.photoview

import android.widget.ImageView

/**
 *@package:com.wy.younger.imageviewer.photoview
 *@data on:2019/2/2 10:24
 *author:YOUNG
 *desc:TODO
 */
interface OnPhotoTapListener {


    /**
     * photoDe点击事件
     * @param view:imageview控件
     * @param x:
     * @param y:
     */
    fun onPhotoTap(view: ImageView, x: Float, y: Float)

}