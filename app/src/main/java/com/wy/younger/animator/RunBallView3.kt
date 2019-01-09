package com.wy.younger.animator

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.wy.younger.utils.ColUtils

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

    var balls: ArrayList<MoveBall>? = null
    var ball: MoveBall? = null

    val defaultR: Float = 100f//默认小球半径
    val defaultColor: Int = Color.BLUE//默认小球颜色
    val defaultVX: Float = 10f//默认小球x方向速度
    val defaultF: Float = 0.95f//碰撞损耗
    val defaultVY: Float = 0f//默认小球y方向速度
    val defaultAY: Float = 0.5f//默认小球加速度

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

        ball = initBall()
        ball?.x = 200f
        ball?.y = 200f
        balls = ArrayList()
        balls?.add(ball!!)

        path = Path()
        paint = Paint(Paint.ANTI_ALIAS_FLAG)

        paint_bg = Paint(Paint.ANTI_ALIAS_FLAG)
        paint_bg?.color = Color.GRAY
        paint_bg?.style = Paint.Style.STROKE
        animator = ValueAnimator.ofFloat(0f, 1f)
        animator?.interpolator = LinearInterpolator()
        animator?.addUpdateListener {

            if (balls?.size == 0) {
                ball = initBall()
                ball?.x = 200f
                ball?.y = 200f
                balls?.add(ball!!)
            }

            updateBalls()
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
        balls?.forEach {
            paint?.color = it.color
            canvas?.drawCircle(it.x, it.y, it.r, paint)
        }
    }

    fun updateBalls() {


        val copyBalls = balls?.clone() as ArrayList<MoveBall>
        copyBalls?.forEach {
            if (it.r < 1) {
                balls?.remove(it)
                return@forEach
            }

            //位移=起始点+速度*时间(默认1)
            //速度=起始速度+加速度*时间(默认1)
            it.x += it.vX
            it.y += it.vY
            it.vX += it.aX
            it.vY += it.aY

            if (it.x > mMaxX) {
                val newBall = it.clone() as MoveBall//新建一个ball同等信息的球
                newBall.r = (newBall.r * 0.9).toFloat()
                newBall.vX = -newBall.vX
                newBall.vY = -newBall.vY
                balls?.add(newBall)

                it.x = mMaxX
                it.vX = -it.vX * defaultF
                it.color = ColUtils.randomRGB()//更改颜色
                it.r = it.r / 2
            }

            if (it.x < mMinX) {
                val newBall = it.clone() as MoveBall
                newBall.r = newBall.r / 2
                newBall.vX = -newBall.vX
                newBall.vY = -newBall.vY
                balls?.add(newBall)

                it.x = mMinX
                it.vX = -it.vX * defaultF
                it.color = ColUtils.randomRGB()
                it.r = it.r / 2
            }
            if (it.y > mMaxY) {

                it.y = mMaxY
                it.vY = -it.vY * defaultF
                it.color = ColUtils.randomRGB()
            }
            if (it.y < mMinY) {
                it.y = mMinY
                it.vY = -it.vY * defaultF
                it.color = ColUtils.randomRGB()
            }

        }
    }


    private fun initBall(): MoveBall {
        val mBall = MoveBall()
        mBall.color = defaultColor
        mBall.r = defaultR
        mBall.vX = defaultVX
        mBall.vY = defaultVY
        mBall.aY = defaultAY
        mBall.x = 0f
        mBall.y = 0f
        return mBall
    }


}