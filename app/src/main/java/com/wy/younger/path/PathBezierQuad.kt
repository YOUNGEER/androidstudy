package com.wy.younger.path

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**

 * 二阶贝塞尔:quadTo(x1,y1,x2,y2),加上起始点，实际上需要是三个点进行控制的

 */
class PathBezierQuad : View {

    var point1 = Point(200, 200)
    var point2 = Point(400, 800)
    var point3 = Point(800, 200)


    var paint: Paint? = null
    var paint_bg: Paint? = null
    var paint_point: Paint? = null
    var path: Path? = null


    //辅助线 paint
    var paint_line: Paint? = null
    var path_line: Path? = null


    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initView()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttrs: Int) : super(
        context,
        attributeSet,
        defStyleAttrs
    ) {
        initView()
    }


    fun initView() {

        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint?.style = Paint.Style.STROKE
        paint?.color = Color.RED
        paint?.strokeWidth = 5f


        paint_line = Paint(Paint.ANTI_ALIAS_FLAG)
        paint_line?.style = Paint.Style.STROKE
        paint_line?.color = Color.YELLOW
        paint_line?.strokeWidth = 2f


        paint_point = Paint(Paint.ANTI_ALIAS_FLAG)
        paint_point?.style = Paint.Style.STROKE
        paint_point?.color = Color.BLACK
        paint_point?.strokeWidth = 15f


        paint_bg = Paint()
        paint_bg?.color = Color.CYAN
        path = Path()
        path_line = Path()

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_UP -> {
                path?.reset()
                path_line?.reset()
                point2.x = event.x.toInt()
                point2.y = event.y.toInt()
                invalidate()
            }
        }

        return true

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(0f, 0f, 1000f, 1000f, paint_bg)

        path?.moveTo(point1.x.toFloat(), point1.y.toFloat())
        path?.quadTo(point2.x.toFloat(), point2.y.toFloat(), point3.x.toFloat(), point3.y.toFloat())

        canvas?.drawPath(path, paint)

        path_line?.moveTo(point1.x.toFloat(), point1.y.toFloat())
        path_line?.lineTo(point2.x.toFloat(), point2.y.toFloat())
        path_line?.lineTo(point3.x.toFloat(), point3.y.toFloat())

        canvas?.drawPath(path_line, paint_line)

        canvas?.drawPoints(
            floatArrayOf(
                point1.x.toFloat(), point1.y.toFloat()
                , point2.x.toFloat(), point2.y.toFloat()
                , point3.x.toFloat(), point3.y.toFloat()
            ), paint_point
        )


    }

}