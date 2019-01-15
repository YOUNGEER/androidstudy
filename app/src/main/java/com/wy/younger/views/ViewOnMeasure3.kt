package com.wy.younger.views

import android.content.Context
import android.content.res.Configuration
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.*
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
 * 3.继承viewgroup,重写onMeasure和onLayout来实现内部逻辑
 *
 */
class ViewOnMeasure3 : ViewGroup {


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


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        //计算子view时，主要是结合子view的要求和父view的剩余空间的综合起来
        //子view的要求就是layout开始的属性，也就是我们开发者要求的规格大小
        //同时开发者的要求我绝对高于父view的剩余空间的
        for (i in 0 until childCount) {

            var childView = getChildAt(i) as View
            var lp = childView.layoutParams   //这个参数可以获取xml中对应的layout开发的属性
            when (lp.width) {
                MeasureSpec.EXACTLY -> {

                }
            }


        }

    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }

}