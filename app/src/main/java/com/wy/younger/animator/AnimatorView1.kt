package com.wy.younger.animator

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.wy.younger.ScreenWidht

/**
 * @package:com.learn.android.animator
 * @data on:2019/1/3 13:55
 * author:YOUNG
 * desc:通过valueAnimator的addUpdateListener刷新view的半径大小，实现动画
 *
 */
class AnimatorView1 : View {
    var mPaint: Paint? = null
    var mValueAnimator: ValueAnimator? = null
    var mRadius: Float = 20f

    var x: Float? = 0f

    var path: Path? = null

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {

        path = Path()

        mPaint = Paint()
        mPaint?.color = Color.RED
        mPaint?.style = Paint.Style.FILL
        mValueAnimator = ValueAnimator.ofFloat(0f, 1f)
        mValueAnimator?.repeatCount = 20
        mValueAnimator?.duration = 3000
        mValueAnimator?.repeatMode = ValueAnimator.REVERSE
        mValueAnimator?.addUpdateListener {
            x = it.animatedValue as Float * context.ScreenWidht()

            path?.reset()

            path?.addCircle(x!!, y, mRadius, Path.Direction.CW)
            invalidate()
        }
        mValueAnimator?.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.translate(0f, 300f)
        canvas?.drawPath(path, mPaint)
    }

}
