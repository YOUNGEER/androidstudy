package com.wy.younger.path

import android.content.Context
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class Path多边形 : View {
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

    }


    /**
     * n角星路径
     *
     * @param num 几角星
     * @param R   外接圆半径
     * @param r   内接圆半径
     * @return n角星路径
     */
    public fun nStarPath(num: Int, R: Float, r: Float): Path {
        var path = Path()
        val perDeg: Float = 360.0f / num;
        val degA: Float = perDeg / 2.0f / 2.0f
        val degB: Float = 360 * 1.0f / (num - 1) / 2.0f - degA / 2.0f + degA
        path.moveTo(
            (Math.cos(rad(degA + perDeg * 0).toDouble()) * R).toFloat() + (R * Math.cos(rad(degA).toDouble())).toFloat(),
            (-Math.sin(rad(degA + perDeg * 0).toDouble()) * R + R).toFloat()
        )
        for (i in 1..num - 1) {
            path.lineTo(
                (Math.cos(rad(degA + perDeg * i)) * R + R * Math.cos(rad(degA))),
                (-Math.sin(rad(degA + perDeg * i)) * R + R)
            );
            path.lineTo(
                (Math.cos(rad(degB + perDeg * i)).toFloat() * r + R * Math.cos(rad(degA))),
                (-Math.sin(rad(degB + perDeg * i)) * r + R)
            );
        }
        path.close();
        return path;
    }

    /**
     * 角度制化为弧度制
     *
     * @param deg 角度
     * @return 弧度
     */
    fun rad(deg: Float): Float {
        return (deg * Math.PI / 180).toFloat();
    }

}