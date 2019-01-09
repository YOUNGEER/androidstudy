package com.wy.younger.path

import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.View
import java.util.*

/**

 *

 */
class PathClock : View {

    var mainPaint: Paint? = null

    var hPaint: Paint? = null
    var mPaint: Paint? = null
    var sPaint: Paint? = null

    var sPath: Path? = null

    var mHandler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg?.what == 0) {
                invalidate()
            }
        }
    }


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
        //最外面的大圆

        mainPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mainPaint?.color = Color.GRAY
        mainPaint?.strokeWidth = 8f
        mainPaint?.style = Paint.Style.STROKE
        mainPaint?.strokeCap = Paint.Cap.ROUND

        //小时
        hPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        hPaint?.color = Color.BLUE
        hPaint?.strokeWidth = 10f
        hPaint?.style = Paint.Style.STROKE
        hPaint?.strokeCap = Paint.Cap.ROUND

        //分钟
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint?.color = Color.BLUE
        mPaint?.strokeWidth = 5f
        mPaint?.style = Paint.Style.STROKE
        mPaint?.strokeCap = Paint.Cap.ROUND

        //分钟
        sPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        sPaint?.color = Color.BLACK
        sPaint?.strokeWidth = 2f
        sPaint?.style = Paint.Style.STROKE
        sPaint?.strokeCap = Paint.Cap.ROUND

        sPath = Path()


        invalidate()

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        canvas?.save()
        canvas?.translate(500f, 500f)
        outArcPath(canvas)
        drawH(canvas)
        drawM(canvas)


        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)
        val sec = calendar.get(Calendar.SECOND)

        drawS(canvas, sec)

        canvas?.restore()

    }


    fun outArcPath(canvas: Canvas?) {
        canvas?.save()
        for (i in 0..3) {
            canvas?.drawArc(RectF(-400f, -400f, 400f, 400f), 22.5f, 45f, false, mainPaint)
            canvas?.rotate(90f)
        }
        canvas?.restore()
    }

    fun drawH(canvas: Canvas?) {
        canvas?.save()
        for (i in 0..11) {
            canvas?.drawPoint(280f, 0f, mainPaint)
            canvas?.drawLine(280f, 0f, 320f, 0f, hPaint)
            canvas?.rotate(30f)
        }
        canvas?.restore()
    }

    fun drawM(canvas: Canvas?) {
        canvas?.save()
        for (i in 0..59) {
            canvas?.drawLine(300f, 0f, 320f, 0f, mPaint)
            canvas?.rotate(6f)
        }
        canvas?.restore()
    }

    fun drawS(canvas: Canvas?, sec: Int) {
        sPath?.reset()
        canvas?.save()
        canvas?.rotate(sec * 6f)
        sPath?.moveTo(0f, 0f)
        sPath?.lineTo(300f, 0f)
        canvas?.drawPath(sPath, sPaint)
        canvas?.restore()
        mHandler.sendEmptyMessageDelayed(0, 1000)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (null != mHandler) {
            mHandler.removeMessages(0)
        }
    }
}