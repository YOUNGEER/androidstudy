package com.wy.younger.path

import android.graphics.Point
import android.graphics.PointF

/**
 * 作者：张风捷特烈<br></br>
 * 时间：2018/11/16 0016:10:25<br></br>
 * 邮箱：1981462002@qq.com<br></br>
 * 说明：审断之神----捷特麾下十二战神之一,负责审判任何罪恶
 */
object JudgeInCircle {
    /**
     * 判断出是否在某点的半径为r圆范围内
     *
     * @param src 目标点
     * @param dst 主动点
     * @param r   半径
     */
    fun judgeCircleArea(src: Point, dst: Point, r: Float): Boolean {
        return disPos2d(src.x.toFloat(), src.y.toFloat(), dst.x.toFloat(), dst.y.toFloat()) <= r
    }

    fun judgeCircleArea(src: PointF, dst: PointF, r: Float): Boolean {
        return disPos2d(src.x, src.y, dst.x, dst.y) <= r
    }

    /**
     * 两点间距离函数
     */
    fun disPos2d(x1: Float, y1: Float, x2: Float, y2: Float): Float {
        return Math.sqrt(((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)).toDouble()).toFloat()
    }

}
