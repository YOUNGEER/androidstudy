package com.wy.younger.animator

import android.graphics.Color

/**
 *@package:com.wy.younger.animator.会动的粒子
 *@data on:2019/1/9 13:44
 *author:YOUNG
 *desc:TODO
 */

class MoveBall : Cloneable {

    var vX: Float = 0f//x速度
    var vY: Float = 0f//y速度

    var aX: Float = 0f//x加速度
    var aY: Float = 0f//y加速度

    var x: Float = 0f//x坐标点
    var y: Float = 0f//y坐标点

    var color: Int = Color.WHITE//小球的颜色
    var r: Float = 0f//小球的半径大小

    override public fun clone(): Any {
        return super.clone()
    }

}