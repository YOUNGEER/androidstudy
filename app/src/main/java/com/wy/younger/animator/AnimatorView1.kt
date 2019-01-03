package com.wy.younger.animator

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.renderscript.Sampler
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 * @package:com.learn.android.animator
 * @data on:2019/1/3 13:55
 * author:YOUNG
 * desc:通过valueAnimator的addUpdateListener刷新view的半径大小，实现动画
 */
class AnimatorView1 : View {
    var mPaint: Paint? = null
    var mValueAnimator: ValueAnimator? = null
    var mRadius: Float = 100f

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {
        mPaint = Paint()
        mPaint?.color = Color.RED
        mPaint?.style = Paint.Style.FILL
        mValueAnimator = ValueAnimator.ofFloat(100f, 200f)
        mValueAnimator?.repeatCount = 20
        mValueAnimator?.duration = 1000
        mValueAnimator?.repeatMode = ValueAnimator.REVERSE
        mValueAnimator?.addUpdateListener {
            mRadius = it.animatedValue as Float
            invalidate()
        }
        mValueAnimator?.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.translate(300f, 300f)
        canvas?.drawCircle(0f, 0f, mRadius, mPaint)

    }


}
