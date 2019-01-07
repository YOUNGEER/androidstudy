package com.wy.younger.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * Paint 线条形状
 *
 * setStrokeWidth:单位为像素，默认值是 0。
 *
 * setStrokeCap:线头形状有三种：BUTT 平头、ROUND 圆头、SQUARE 方头。默认为 BUTT。
 *
 * setStrokeJoin:有三个值可以选择：MITER 尖角、 BEVEL 平角和 ROUND 圆角。默认为 MITER。
 *
 *
 * setStrokeMiter:是对于 setStrokeJoin() 的一个补充，它用于设置 MITER 型拐角的延长线的最大值
 *
 */
class PaintStroke : View {
    var paint: Paint? = null
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
        paint = Paint()
        path = Path()
        path?.moveTo(0f, 0f)
        path?.rLineTo(200f, 0f)
        path?.lineTo(150f, 120f)

        invalidate()

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint?.style = Paint.Style.STROKE

        canvas?.drawCircle(50f, 50f, 50f, paint)
        paint?.strokeWidth = 50f
        canvas?.drawCircle(50f, 150f, 50f, paint)

        paint?.strokeCap = Paint.Cap.SQUARE
        canvas?.drawLine(0f, 250f, 150f, 250f, paint)

        paint?.strokeCap = Paint.Cap.ROUND
        canvas?.drawLine(0f, 350f, 150f, 350f, paint)


        paint?.strokeCap = Paint.Cap.BUTT
        canvas?.drawLine(0f, 450f, 150f, 450f, paint)


        canvas?.save()

        canvas?.translate(100f, 1000f)
        paint?.strokeJoin = Paint.Join.MITER
        canvas?.drawPath(path, paint)

        canvas?.translate(300f, 0f)
        paint?.strokeJoin = Paint.Join.BEVEL
        canvas?.drawPath(path, paint)

        canvas?.translate(300f, 0f)
        paint?.strokeJoin = Paint.Join.ROUND
        canvas?.drawPath(path, paint)

        canvas?.restore()


    }
}