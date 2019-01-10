package com.wy.younger.Bitmap

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.wy.younger.R
import com.wy.younger.ScreenHeight
import com.wy.younger.ScreenWidht
import com.wy.younger.animator.MoveBall

/**
 *@package:com.wy.younger.canvas
 *@data on:2019/1/4 14:16
 *author:YOUNG
 *desc:TODO
 */

/**
 * 一张jpg或png图片就是很多颜色的合集，而这些合集信息都被封装到了Bitmap类中
 * 你可以使用Bitmap获取任意像素点，并修改它，对与某像素点而言，颜色信息是其主要的部分
 *
 *利用每个circle来复刻出了图片，因为图片像素比较大，所以去每4位取其中的一个颜色点作为circle的color
 *
 * 来搞个炫酷的动画吧
 *
 *
 *
 */
class BitmapColorSplit : View {

    var bitmap: Bitmap? = null
    var paint: Paint? = null
    var balls: ArrayList<MoveBall>? = null

    val mMaxX: Float = context.ScreenWidht().toFloat()//X最大值
    val mMinX: Float = 0f//X最小值
    val mMaxY: Float = context.ScreenHeight().toFloat()//Y最大值
    val mMinY: Float = 0f//Y最小值

    var valueAnimator: ValueAnimator? = null

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

        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        balls = ArrayList()

        bitmap = BitmapFactory.decodeResource(resources, R.drawable.pic1)

        for (i in 0..(bitmap!!.width / 16 - 1)) {
            for (j in 0..(bitmap!!.height / 16 - 1)) {
                val color_0_0 = bitmap?.getPixel(i * 16, j * 16) as Int//获取坐标0，0点的像素颜色值
                val ball = MoveBall()

                ball.color = color_0_0
                ball.r = 8f
                ball.x = (i + 1) * 8f
                ball.y = (j + 1) * 8f
                ball.aY = 0.98f

                if (Math.random() > 0.5) {
                    ball.vX = -(Math.random() * 30).toFloat()
                } else {
                    ball.vX = (Math.random() * 30).toFloat()
                }
                ball.vY = (Math.random() * 30).toFloat()

                balls?.add(ball)
            }
        }

        valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator?.addUpdateListener {
            updateBalls()
            invalidate()
        }
        valueAnimator?.duration = 1000
        valueAnimator?.repeatCount = 100
        valueAnimator?.repeatMode = ValueAnimator.RESTART


        invalidate()

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            valueAnimator?.start()
        }
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        balls?.forEach {
            paint?.color = it.color

            Log.i("gdsfsdfsdfsadfsad", "${it.color}")

            canvas?.drawCircle(it.x, it.y, it.r, paint)
        }
    }


    fun updateBalls() {
        balls?.forEach {
            //位移=起始点+速度*时间(默认1)
            //速度=起始速度+加速度*时间(默认1)
            it.x += it.vX
            it.y += it.vY
            it.vX += it.aX
            it.vY += Math.random().toFloat()
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

}