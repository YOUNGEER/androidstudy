package com.wy.younger.path

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * path表示路径，常规的方法就是点，线，面的操作
 */
class PathPolygon : View {
    var pathFive: Path? = null
    var pathSix: Path? = null
    var pathSeven: Path? = null
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

        paint = Paint(Paint.ANTI_ALIAS_FLAG)

        paint?.style = Paint.Style.STROKE
        paint?.color = Color.RED

        pathFive = nStarPath(5, 150f, 100f)
        pathSix = nStarPath(6, 200f, 80f)
        pathSeven = nStarPath(7, 120f, 80f)
    }


    fun pathApis() {
        pathFive?.moveTo(0f, 0f)//移动到0，0
        pathFive?.lineTo(0f, 0f)//链接到0，0
        pathFive?.rLineTo(0f, 0f)//移动了0，0
        pathFive?.close()//链接到初始位置

        //canvas可以绘制各种图片，其实也可以通过path来绘制，一系列add方法，主要需要注意的是 最后一个参数Direction
        // 参考 pathDirection.kt

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()
        canvas?.translate(200f, 100f)
        canvas?.drawPath(pathFive, paint)

        canvas?.translate(0f, 500f)
        canvas?.drawPath(pathSix, paint)

        canvas?.translate(0f, 500f)
        canvas?.drawPath(pathSeven, paint)

        canvas?.restore()

    }

    /**
     * n角星路径
     *
     * @param num 几角星
     * @param R   外接圆半径
     * @param r   内接圆半径
     * @return n角星路径
     */
    fun nStarPath(num: Int, R: Float, r: Float): Path {
        var path = Path()
        val perDeg: Float = 360.0f / num
        val degA: Float = perDeg / 2.0f / 2.0f
        val degB: Float = 360 * 1.0f / (num - 1) / 2.0f - degA / 2.0f + degA
        path.moveTo(
            (Math.cos(rad(degA + perDeg * 0).toDouble()) * R).toFloat() + (R * Math.cos(rad(degA).toDouble())).toFloat(),
            (-Math.sin(rad(degA + perDeg * 0).toDouble()) * R + R).toFloat()
        )
        for (i in 0 until num) {
            path.lineTo(
                (Math.cos(rad(degA + perDeg * i).toDouble()).toFloat() * R + R * Math.cos(rad(degA).toDouble()).toFloat()),
                (-Math.sin(rad(degA + perDeg * i).toDouble()) * R + R).toFloat()
            )
            path.lineTo(
                (Math.cos(rad(degB + perDeg * i).toDouble()).toFloat() * r + R * Math.cos(rad(degA).toDouble()).toFloat()),
                (-Math.sin(rad(degB + perDeg * i).toDouble()) * r + R).toFloat()
            )
        }
        path.close()
        return path

    }

    /**
     * 角度制化为弧度制
     *
     * @param deg 角度
     * @return 弧度
     */
    fun rad(deg: Float): Float {
        return (deg * Math.PI / 180).toFloat()
    }


    /**
     * 画正n角星的路径:
     *
     * @param num 角数
     * @param R   外接圆半径
     * @return 画正n角星的路径
     */
    fun regularStarPath(num: Int, R: Float): Path {
        var degA: Float
        var degB: Float
        if (num % 2 == 1) {//奇数和偶数角区别对待
            degA = 360.0f / num / 2 / 2
            degB = 180 - degA - 360.0f / num / 2
        } else {
            degA = 360.0f / num / 2
            degB = 180 - degA - 360.0f / num / 2
        }
        val r: Float = R * Math.sin(rad(degA).toDouble()).toFloat() / Math.sin(rad(degB).toDouble()).toFloat()
        return nStarPath(num, R, r)
    }


    /**
     * 画正n边形的路径
     *
     * @param num 边数
     * @param R   外接圆半径
     * @return 画正n边形的路径
     */
    fun regularPolygonPath(num: Int, R: Float): Path {
        val r = R * (Math.cos(rad(360.0f / num / 2).toDouble())).toFloat()
        return nStarPath(num, R, r)
    }


}