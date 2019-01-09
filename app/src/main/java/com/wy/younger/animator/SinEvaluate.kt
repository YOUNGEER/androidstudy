package com.wy.younger.animator

import android.animation.TypeEvaluator

/**
 *@package:com.wy.younger.animator.movingparticles
 *@data on:2019/1/9 14:05
 *author:YOUNG
 *desc:TODO
 */
class SinEvaluate : TypeEvaluator<MoveBall> {
    override fun evaluate(fraction: Float, startValue: MoveBall?, endValue: MoveBall?): MoveBall {
        val deletX = endValue!!.x - startValue!!.x
        val deletY = endValue.y - endValue.x


        var ball = startValue.clone() as MoveBall
        val x = startValue.x + fraction * (deletX)


        val y = (Math.sin(x * Math.PI / 180) * 100).toFloat()
        ball.x = x
        ball.y = y

        return ball

    }

}