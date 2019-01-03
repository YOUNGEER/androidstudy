package com.wy.younger.animator

import android.animation.TypeEvaluator

/**
 *@package:com.wy.younger.animator
 *@data on:2019/1/3 17:08
 *author:YOUNG
 *desc:TODO
 */
class BallEvaluate : TypeEvaluator<Ball> {

    override fun evaluate(fraction: Float, startValue: Ball?, endValue: Ball?): Ball {

        val startBall: Ball? = startValue
        val endBall: Ball? = endValue

        var ball = Ball()

        //半径=初始+分率*(结尾-初始) 比如运动到一半，分率是0.5
        ball.radius = (startBall!!.radius + fraction * (endBall!!.radius - startBall!!.radius));
        //颜色怎么渐变?
        ball.color = evaluateColor(fraction, startBall!!.color, endBall!!.color);

        return ball

    }

    fun evaluateColor(fraction: Float, startValue: Any, endValue: Any): Int {
        val startInt = startValue as Int
        var startA: Float = ((startInt.shr(24)) and 0xff) / 255.0f
        var startR: Float = ((startInt.shr(16)) and 0xff) / 255.0f
        var startG: Float = ((startInt.shr(8)) and 0xff) / 255.0f
        var startB: Float = (startInt and 0xff) / 255.0f

        val endInt = endValue as Int
        var endA: Float = ((endInt.shr(24)) and 0xff) / 255.0f
        var endR: Float = ((endInt.shr(16)) and 0xff) / 255.0f
        var endG: Float = ((endInt.shr(8)) and 0xff) / 255.0f
        var endB: Float = (endInt and 0xff) / 255.0f

        // convert from sRGB to linear

        startR = Math.pow(startR.toDouble(), 2.2).toFloat()
        startG = Math.pow(startG.toDouble(), 2.2).toFloat()
        startB = Math.pow(startB.toDouble(), 2.2).toFloat()

        endR = Math.pow(endR.toDouble(), 2.2).toFloat()
        endG = Math.pow(endG.toDouble(), 2.2).toFloat()
        endB = Math.pow(endB.toDouble(), 2.2).toFloat()

        // compute the interpolated color in linear space
        var a: Float = startA + fraction * (endA - startA)
        var r: Float = startR + fraction * (endR - startR)
        var g: Float = startG + fraction * (endG - startG)
        var b: Float = startB + fraction * (endB - startB)

        // convert back to sRGB in the [0..255] range
        a = a * 255.0f
        r = (Math.pow(r.toDouble(), 1.0 / 2.0) * 255.0f).toFloat()
        g = (Math.pow(g.toDouble(), 1.0 / 2.0) * 255.0f).toFloat()
        b = (Math.pow(b.toDouble(), 1.0 / 2.0) * 255.0f).toFloat()

        return Math.round(a).shl(24) or Math.round(r).shl(16) or Math.round(g).shl(8) or Math.round(b)
    }


}