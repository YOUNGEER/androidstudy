package com.wy.younger.animator

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.wy.younger.ScreenWidht

/**
 *@package:com.wy.younger.animator.movingparticles
 *@data on:2019/1/9 14:04
 *author:YOUNG
 *desc:TODO
 */
/**
 * 小球按照sin路径进行变换
 */
class RunBallView1 : View {

    var tempBall: MoveBall? = null
    var path: Path? = null
    var paint: Paint? = null

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initView(context)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttrs: Int) : super(
        context,
        attributeSet,
        defStyleAttrs
    ) {

        initView(context)
    }

    fun initView(context: Context) {
        path = Path()
        paint = Paint(Paint.ANTI_ALIAS_FLAG)

        var startBall = MoveBall()
        startBall.color = Color.RED
        startBall.r = 30f
        startBall.x = startBall.r / 2

        var endBall = startBall.clone() as MoveBall
        endBall.x = context.ScreenWidht().toFloat() - endBall.r / 2
        endBall.y = 500f


        var animator = ValueAnimator.ofObject(SinEvaluate(), startBall, endBall)

        animator.interpolator = LinearInterpolator()

        animator.addUpdateListener {
            tempBall = it.animatedValue as MoveBall
            path?.reset()
            path?.addCircle(tempBall!!.x, tempBall!!.y, tempBall!!.r, Path.Direction.CW)
            paint?.color = tempBall!!.color
            invalidate()
        }

        animator.duration = 5000
        animator.repeatCount = 10
        animator.reverse()
        animator.start()

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.translate(0f, 200f)
        canvas?.drawPath(path, paint)

    }
}