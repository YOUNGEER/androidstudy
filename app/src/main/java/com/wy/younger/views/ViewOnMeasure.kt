package com.wy.younger.views

import android.content.Context
import android.content.res.Configuration
import android.graphics.Canvas
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.VelocityTracker
import android.view.View
import android.view.ViewConfiguration
import android.widget.Scroller

/**
 * 该类主要介绍 自定义view三部曲之一
 * onMeasure方法  测量
 */
class ViewOnMeasure : View {

    var viewConfiguration: ViewConfiguration? = null

    var configuration: Configuration? = null

    var gestureDetector: GestureDetector? = null

    var scroller: Scroller? = null

    var velocityTracker: VelocityTracker? = null

    var viewDragHelper: ViewDragHelper? = null


    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {

    }

    fun initView() {

    }

    /**
     * 1.修改已有的计算算法的尺寸   比如自定义imageView
     *
     * 2.完全实现自己的算法   比如自定义view
     */

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)


        MeasureSpec.AT_MOST
        MeasureSpec.EXACTLY
        MeasureSpec.UNSPECIFIED

        val mode: Int = MeasureSpec.getMode(widthMeasureSpec)
        val size: Int = MeasureSpec.getSize(widthMeasureSpec)


        MeasureSpec.makeMeasureSpec(mode, size)

    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }


}