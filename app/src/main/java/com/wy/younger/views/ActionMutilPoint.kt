package com.wy.younger.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 *@package:com.wy.younger.views
 *@data on:2019/1/11 17:49
 *author:YOUNG
 *desc:TODO
 */
class ActionMutilPoint : View {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttrs: Int) : super(
        context,
        attributeSet,
        defStyleAttrs
    )


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        /**
         * 1、从 0 开始，自动增长。
        2、如果之前落下的手指抬起，后面手指的 Index 会随之减小。
        3、Index 变化趋向于第一次落下的数值(落下手指时，前面有空缺会优先填补空缺)。
        4、对 move 事件无效。
         */
        val pointId = event?.actionIndex

        when (event?.actionMasked) {
            MotionEvent.ACTION_MOVE -> {

            }


            MotionEvent.ACTION_DOWN -> {

            }

            MotionEvent.ACTION_POINTER_DOWN -> {
                Log.i("gdsafsdaaaafsdafasdf", "di $pointId down")
            }
            MotionEvent.ACTION_POINTER_UP -> {
                Log.i("gdsafsdaaaafsdafasdf", "di $pointId up")

            }


        }

        return true
    }
}