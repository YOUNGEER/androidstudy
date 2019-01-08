package com.wy.younger.path

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 * 一阶贝塞尔：lineTo(x1,y1)
 *
 * 二阶贝塞尔:quadTo(x1,y1,x2,y2)
 *
 * 三阶贝塞尔:cubicTo(x1,y1,x2,y2,x3,y3)
 */
class PathBezier : View {

    var point1 = Point(100, 100)
    var point2 = Point(800, 100)
    var point3 = Point(100, 800)
    var point4 = Point(800, 800)


    var paint: Paint? = null
    var paint_bg: Paint? = null
    var paintLine: Paint? = null


    var path: Path? = null


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
        paint?.strokeWidth = 10f

        paintLine = Paint(Paint.ANTI_ALIAS_FLAG)
        paintLine?.style = Paint.Style.STROKE
        paintLine?.color = Color.GREEN
        paintLine?.strokeWidth = 2f



        paint_bg = Paint()
        paint_bg?.color = Color.CYAN
        path = Path()


    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return when (event?.action) {
            MotionEvent.ACTION_UP -> {
                Log.i("gafsdfdsfadsfdsa", "moveeeeeee")

                path?.reset()
                point3.x = event.x.toInt()
                point3.y = event.y.toInt()
                invalidate()
                true
            }
            else -> true
        }

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)



        canvas?.drawRect(0f, 0f, 800f, 800f, paint_bg)
        path?.moveTo(point1.x.toFloat(), point1.y.toFloat())
        path?.lineTo(point2.x.toFloat(), point2.y.toFloat())


        path?.moveTo(point1.x.toFloat(), point1.y.toFloat())
        path?.quadTo(point2.x.toFloat(), point2.y.toFloat(), point3.x.toFloat(), point3.y.toFloat())

        path?.moveTo(point1.x.toFloat(), point1.y.toFloat())
        path?.cubicTo(
            point2.x.toFloat(),
            point2.y.toFloat(),
            point3.x.toFloat(),
            point3.y.toFloat(),
            point4.x.toFloat(),
            point4.y.toFloat()
        )

        canvas?.drawPath(path, paint)


    }

}