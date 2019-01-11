package com.wy.younger.canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.wy.younger.ScreenHeight
import com.wy.younger.ScreenWidht


/**
 *@package:com.wy.younger.canvas
 *@data on:2019/1/11 11:14
 *author:YOUNG
 *desc:Region 直接翻译的意思是 地域，区域。在此处应该是区域的意思。
 * 它和 Path 有些类似，但 Path 可以是不封闭图形，而 Region 总是封闭的。
 * 可以通过 setPath 方法将 Path 转换为 Region
 *
 * Region 中的 contains 方法，这个方法可以判断一个点是否包含在该区域内
 *
 */

class CanvasRegion : View {

    var pathCenter: Path? = null
    var pathLeft: Path? = null
    var pathTop: Path? = null
    var pathRight: Path? = null
    var pathBottom: Path? = null

    var mMapMatrix: Matrix? = null

    var paint: Paint? = null
    var paintOuter: Paint? = null


    val centerX = context.ScreenWidht()
    val centerY = context.ScreenHeight()

    var regionCenter: Region? = null
    var regionLeft: Region? = null
    var regionTop: Region? = null
    var regionRight: Region? = null
    var regionBottom: Region? = null

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
        pathCenter = Path()
        pathLeft = Path()
        pathTop = Path()
        pathRight = Path()
        pathBottom = Path()

        regionCenter = Region()
        regionLeft = Region()
        regionTop = Region()
        regionRight = Region()
        regionBottom = Region()

        mMapMatrix = Matrix()

        val region = Region(-180, -180, 180, 180)


        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint?.color = Color.GRAY
        paint?.style = Paint.Style.FILL

        pathCenter?.addCircle(0f, 0f, 100f, Path.Direction.CW)

        paintOuter = Paint(Paint.ANTI_ALIAS_FLAG)
        paintOuter?.color = Color.GRAY
        paintOuter?.style = Paint.Style.STROKE
        paintOuter?.strokeWidth = 100f


        pathLeft?.addArc(RectF(-180f, -180f, 180f, 180f), 50f, 80f)
        pathTop?.addArc(RectF(-180f, -180f, 180f, 180f), 140f, 80f)
        pathRight?.addArc(RectF(-180f, -180f, 180f, 180f), 230f, 80f)
        pathBottom?.addArc(RectF(-180f, -180f, 180f, 180f), -40f, 80f)


        regionLeft?.setPath(pathLeft, region)
        regionTop?.setPath(pathTop, region)
        regionRight?.setPath(pathRight, region)
        regionBottom?.setPath(pathBottom, region)


    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val pts = FloatArray(2)
        pts[0] = event!!.rawX
        pts[1] = event!!.rawY
        mMapMatrix?.mapPoints(pts)

        val x = pts[0].toInt()
        val y = pts[1].toInt()


        Log.i("fdsfsdgdsafas", "${event?.action},x:$x,y:$y")
        when (event?.action) {
            MotionEvent.ACTION_UP -> {

                var type = -1

                when {
                    regionLeft?.contains(x!!.toInt(), y!!.toInt())!! -> type = 1
                    regionTop?.contains(x!!.toInt(), y!!.toInt())!! -> type = 2
                    regionRight?.contains(x!!.toInt(), y!!.toInt())!! -> type = 3
                    regionBottom?.contains(x!!.toInt(), y!!.toInt())!! -> type = 4
                    regionCenter?.contains(x!!.toInt(), y!!.toInt())!! -> type = 0
                }

                Log.i("fdsfsdgdsafas", "click region:$type")

            }
        }
        return true
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)



        canvas?.translate(centerX.toFloat() / 2, centerY.toFloat() / 2)

        // 获取测量矩阵(逆矩阵)
        if (mMapMatrix!!.isIdentity) {
            canvas?.matrix?.invert(mMapMatrix)
        }

        canvas?.drawPath(pathCenter, paint)

        canvas?.drawPath(pathLeft, paintOuter)
        canvas?.drawPath(pathTop, paintOuter)
        canvas?.drawPath(pathRight, paintOuter)
        canvas?.drawPath(pathBottom, paintOuter)

    }
}