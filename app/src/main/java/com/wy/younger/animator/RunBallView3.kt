package com.wy.younger.animator

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

/**
 *@package:com.wy.younger.animator.movingparticles
 *@data on:2019/1/9 14:04
 *author:YOUNG
 *desc:TODO
 */
/**
 * N多小球重叠的效果
 */
class RunBallView3 : View {

    var path: Path? = null
    var paint: Paint? = null
    var paint_bg: Paint? = null
    var animator: ValueAnimator? = null


    var ball1: MoveBall? = null
    var ball2: MoveBall? = null


    val mMaxX: Float = 900f//X最大值
    val mMinX: Float = 100f//X最小值
    val mMaxY: Float = 900f//Y最大值
    val mMinY: Float = 100f//Y最小值


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

        ball1 = initBall1()
        ball2 = initBall2()

        path = Path()
        paint = Paint(Paint.ANTI_ALIAS_FLAG)

        paint_bg = Paint(Paint.ANTI_ALIAS_FLAG)
        paint_bg?.color = Color.GRAY
        paint_bg?.style = Paint.Style.STROKE
        animator = ValueAnimator.ofFloat(0f, 1f)
        animator?.interpolator = LinearInterpolator()
        animator?.addUpdateListener {
            towBall()
            updateBalls(ball1)
            updateBalls(ball2)
            invalidate()
        }

        animator?.duration = 5000
        animator?.repeatCount = 100
        animator?.repeatMode = ValueAnimator.REVERSE
        animator?.start()


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRect(RectF(mMinX, mMinY, mMaxX, mMaxY), paint_bg)
        paint?.color = ball1!!.color
        canvas?.drawCircle(ball1!!.x, ball1!!.y, ball1!!.r, paint)

        paint?.color = ball2!!.color
        canvas?.drawCircle(ball2!!.x, ball2!!.y, ball2!!.r, paint)

    }

    fun updateBalls(ball: MoveBall?) {
        ball?.let {
            //位移=起始点+速度*时间(默认1)
            //速度=起始速度+加速度*时间(默认1)
            it.x += it.vX
            it.y += it.vY
            if (it.x > mMaxX) {
                it.x = mMaxX
                it.vX = -it.vX
            }
            if (it.x < mMinX) {
                it.x = mMinX
                it.vX = -it.vX
            }
            if (it.y > mMaxY) {
                it.y = mMaxY
                it.vY = -it.vY
            }
            if (it.y < mMinY) {
                it.y = mMinY
                it.vY = -it.vY
            }

        }
    }

    fun towBall() {
        val squars = Math.sqrt(
            (Math.abs(ball1!!.x) - Math.abs(ball2!!.x)).toDouble()
                    * (Math.abs(ball1!!.x) - Math.abs(ball2!!.x)).toDouble()
        ) + Math.sqrt(
            (Math.abs(ball1!!.y) - Math.abs(ball2!!.y)).toDouble()
                    * (Math.abs(ball1!!.y) - Math.abs(ball2!!.y)).toDouble()
        )
        if (squars <= 160) {
            ball1!!.vX = -ball1!!.vX
            ball1!!.vY = -ball1!!.vY
            ball2!!.vX = -ball2!!.vX
            ball2!!.vY = -ball2!!.vY

        }
    }


    private fun initBall1(): MoveBall {
        val mBall = MoveBall()
        mBall.color = Color.BLUE
        mBall.r = 50f
        mBall.vX = 11f
        mBall.vY = 7f
        mBall.x = 0f
        mBall.y = 0f
        return mBall
    }

    private fun initBall2(): MoveBall {
        val mBall = MoveBall()
        mBall.color = Color.RED
        mBall.r = 50f
        mBall.vX = 9f
        mBall.vY = 5f
        mBall.x = 600f
        mBall.y = 430f
        return mBall
    }


}