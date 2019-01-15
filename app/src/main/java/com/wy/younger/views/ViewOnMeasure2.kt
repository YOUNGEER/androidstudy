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
 *
 *
 * onMeasure一般按照自定义view的三种类型，也有三种不同的实现方式
 *
 *
 * 1.继承具体的view
 *
 *
 * 2.继承 view
 *
 *
 * 3.继承viewgroup
 *
 */
class ViewOnMeasure2 : View {

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
     * 自己实现 宽，高的大小逻辑
     *
     * 满足父view的限制

     * @param widthMeasureSpec 这个参数是父view的传递过来的参数，里面有父view的测试规格和大小
     *
     * 具体是
     */

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //1.不需要调用super方法，
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)


        //2.计算自己的尺寸大小

        val width = 500
        val height = 500


        //3.自己尺寸和父view的限制进行一个对比处理得到合适的尺寸，可以查看源码分析一波
        resolveSize(width, widthMeasureSpec)
        resolveSize(height, heightMeasureSpec)

        //4.设置了这个代码后，宽，高的值才会生效
        setMeasuredDimension(width, height)

    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }


}