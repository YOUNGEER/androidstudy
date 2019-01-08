package com.wy.younger.path

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**

 *

 */
class PathClock : View {

    var mainPath: Path? = null
    var mainPaint: Paint? = null


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
        mainPath = Path()
        mainPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mainPaint?.color = Color.GRAY
        mainPaint?.strokeWidth = 5f
        outArcPath()


        invalidate()

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawPath(mainPath, mainPaint)

    }


    fun outArcPath() {
        mainPath?.addArc(RectF(100f, 100f, 1000f, 100f), -67.5f, 45f)
        mainPath?.addArc(RectF(100f, 100f, 1000f, 100f), 22.5f, 45f)
        mainPath?.addArc(RectF(100f, 100f, 1000f, 100f), 112.5f, 45f)
        mainPath?.addArc(RectF(100f, 100f, 1000f, 100f), 202.5f, 45f)
    }


}