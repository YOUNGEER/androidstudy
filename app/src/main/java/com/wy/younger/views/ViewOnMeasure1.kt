package com.wy.younger.views

import android.content.Context
import android.content.res.Configuration
import android.graphics.Canvas
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.widget.ImageView
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
class ViewOnMeasure1 : ImageView {

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
     */

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //1.先执行原有的测量算法
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)


        //-=----------------------------------------------------------------------------------------------

        //log打印，widthMeasureSpec是父view调用子view的onMeasure方法时传递过来的参数，但是，
        // 该参数是由子view的layout_width和layout_parent来进行决定的，xml中很多属性是给父view识别调用的


        val mode = MeasureSpec.getMode(widthMeasureSpec)

        val mode2 = MeasureSpec.getMode(heightMeasureSpec)
        when (mode) {
            MeasureSpec.AT_MOST -> {//wrap_content
                Log.i("gdsfsadfsadfas", "AT_MOST")
            }

            MeasureSpec.EXACTLY -> {//match_parent,具体值
                Log.i("gdsfsadfsadfas", "EXACTLY")
            }

            MeasureSpec.UNSPECIFIED -> {
                Log.i("gdsfsadfsadfas", "UNSPECIFIED")
            }
        }

        when (mode2) {
            MeasureSpec.AT_MOST -> {
                Log.i("gdsfsadfsadfas", "AT_MOST")
            }

            MeasureSpec.EXACTLY -> {
                Log.i("gdsfsadfsadfas", "EXACTLY")
            }

            MeasureSpec.UNSPECIFIED -> {
                Log.i("gdsfsadfsadfas", "UNSPECIFIED")
            }
        }


        //-=----------------------------------------------------------------------------------------------

        //2.获取原先的测量结果
        //只有调用的super.onMeasure中的setMeasureDimension方法，这个值才会是有意义的
        //这是测得的尺寸 ，和onLayout后调用得到的尺寸width和height还是有点不一样的
        val width = measuredWidth
        val height = measuredHeight


        //3.计算出新的尺寸大小
        val bian = Math.min(width, height)


        //4.设置了这个代码后，宽，高的值才会生效
        setMeasuredDimension(bian, bian)




    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }


}