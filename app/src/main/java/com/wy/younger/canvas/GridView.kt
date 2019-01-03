package com.wy.younger.canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager

/**
 * canvas的学习，canvas可以画颜色，点，线，各种形状的图形，文字，图片等
 *
 * 相关api有：
 *
 */
class GridView : View {

    var mGridPaint: Paint? = null//网格画笔
    var mWinSize: Point? = null//屏幕尺寸
    var mCoo: Point? = null//坐标系原点


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
        mWinSize = Point()
        loadWinSize(context, mWinSize)
        mCoo = Point(500, 500)
        mGridPaint = Paint(Paint.ANTI_ALIAS_FLAG)


    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawGrid(canvas, mWinSize, mGridPaint)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }


    /**
     * 获取屏幕尺寸的大小，赋值给winSize的坐标点
     */
    fun loadWinSize(context: Context, winSize: Point?) {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics() as DisplayMetrics

        wm?.defaultDisplay.getMetrics(outMetrics)

        winSize?.x = outMetrics.widthPixels
        winSize?.y = outMetrics.heightPixels
    }


    fun gridPath(step: Int, winSize: Point?): Path {

        val path = Path()
        for (index in 0..(winSize!!.y / step + 1).toInt()) {
            path.moveTo(0f, step * index.toFloat())
            path.lineTo(winSize!!.x.toFloat(), step * index.toFloat())

        }
        for (index in 0..(winSize!!.y / step + 1).toInt()) {
            path.moveTo(step * index.toFloat(), 0f)
            path.lineTo(step * index.toFloat(), winSize!!.y.toFloat())
        }

        return path

    }


    fun drawGrid(canvas: Canvas?, winSize: Point?, paint: Paint?) {
        paint?.strokeWidth = 2f
        paint?.color = Color.GRAY
        paint?.style = Paint.Style.STROKE

        paint?.pathEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
        canvas?.drawPath(gridPath(50, winSize), paint)

    }


}