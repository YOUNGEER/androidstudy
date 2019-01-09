package com.wy.younger.animator

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View

/**
 * @package:com.learn.android.animator
 * @data on:2019/1/3 13:55
 * author:YOUNG
 * desc:通过valueAnimator的addUpdateListener刷新view的颜色，实现动画
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class AnimatorView2 : View {
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
        mValueAnimator = ValueAnimator.ofArgb(0xff94E1F7.toInt(), 0xffF35519.toInt())
        mValueAnimator?.repeatCount = 20
        mValueAnimator?.duration = 1000
        mValueAnimator?.repeatMode = ValueAnimator.REVERSE
        mValueAnimator?.addUpdateListener {
//            mRadius = it.animatedValue as Float

            mPaint?.color=it.animatedValue as Int
            invalidate()
        }
        mValueAnimator?.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.translate(300f, 300f)
        canvas?.drawCircle(0f, 0f, 100f, mPaint)

    }


}
