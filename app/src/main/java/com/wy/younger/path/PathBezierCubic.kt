package com.wy.younger.path

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**

 * 三阶贝塞尔:cubicTo(x1,y1,x2,y2,x3,y3),加上起始点，实际上是通过四个点进行控制的

 */
class PathBezierCubic : View {

    var point1 = Point(200, 200)
    var point2 = Point(200, 800)
    var point3 = Point(800, 800)
    var point4 = Point(800, 200)

    var downPaint = Point(0, 0)


    var paint: Paint? = null
    var paint_point: Paint? = null
    var paint_point_circle: Paint? = null
    var paint_bg: Paint? = null


    var path: Path? = null
    var path_point_circle: Path? = null


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

        paint_point = Paint(Paint.ANTI_ALIAS_FLAG)
        paint_point?.style = Paint.Style.STROKE
        paint_point?.color = Color.BLACK
        paint_point?.strokeWidth = 15f

        paint_point_circle = Paint(Paint.ANTI_ALIAS_FLAG)
        paint_point_circle?.style = Paint.Style.STROKE
        paint_point_circle?.color = Color.BLACK
        paint_point_circle?.strokeWidth = 2f




        paint_bg = Paint()
        paint_bg?.color = Color.CYAN
        path = Path()
        path_point_circle = Path()


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                downPaint.x = event.x.toInt()
                downPaint.y = event.y.toInt()
            }
            MotionEvent.ACTION_MOVE -> {


                if (JudgeInCircle.judgeCircleArea(point1, downPaint, 50f)) {
                    setPos(event, point1)
                    downPaint.x = event.x.toInt()
                    downPaint.y = event.y.toInt()
                    invalidate()
                    return true
                }
                if (JudgeInCircle.judgeCircleArea(point2, downPaint, 50f)) {
                    setPos(event, point2)
                    downPaint.x = event.x.toInt()
                    downPaint.y = event.y.toInt()
                    invalidate()
                    return true
                }
                if (JudgeInCircle.judgeCircleArea(point3, downPaint, 50f)) {
                    setPos(event, point3)
                    downPaint.x = event.x.toInt()
                    downPaint.y = event.y.toInt()
                    invalidate()
                    return true
                }
                if (JudgeInCircle.judgeCircleArea(point4, downPaint, 50f)) {
                    setPos(event, point4)
                    downPaint.x = event.x.toInt()
                    downPaint.y = event.y.toInt()
                    invalidate()
                    return true
                }

            }
        }

        return true

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(0f, 0f, 1000f, 1000f, paint_bg)

        path?.reset()

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

        canvas?.drawPoints(
            floatArrayOf(
                point1.x.toFloat(), point1.y.toFloat()
                , point2.x.toFloat(), point2.y.toFloat()
                , point3.x.toFloat(), point3.y.toFloat()
                , point4.x.toFloat(), point4.y.toFloat()
            ), paint_point
        )

        path_point_circle?.reset()

        path_point_circle?.addCircle(point1.x.toFloat(), point1.y.toFloat(), 50f, Path.Direction.CW)
        path_point_circle?.addCircle(point2.x.toFloat(), point2.y.toFloat(), 50f, Path.Direction.CW)
        path_point_circle?.addCircle(point3.x.toFloat(), point3.y.toFloat(), 50f, Path.Direction.CW)
        path_point_circle?.addCircle(point4.x.toFloat(), point4.y.toFloat(), 50f, Path.Direction.CW)

        canvas?.drawPath(path_point_circle, paint_point_circle)


    }

    /**
     * 设置点位
     *
     * @param event 事件
     * @param p     点位
     */
    fun setPos(event: MotionEvent, p: Point) {
        p.x = event.x.toInt()
        p.y = event.y.toInt()
    }

}