package com.wy.younger.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.ComposeShader
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * PaintPorterduffMode
 */
class PaintPorterduffMode : View {
    var paint: Paint? = null

    var composeShader: ComposeShader? = null

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

        invalidate()
    }


    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)


    }
}