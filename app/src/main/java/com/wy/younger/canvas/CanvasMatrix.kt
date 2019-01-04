package com.wy.younger.canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import com.wy.younger.R

/**
 *@package:com.wy.younger.canvas
 *@data on:2019/1/4 14:16
 *author:YOUNG
 *desc:TODO
 */

/**
 * 主要是学习下canvas matrix变换
 *  Android中的Matrix是一个3 x 3的矩阵
 *  针对每种变换，Android提供了pre、set和post三种操作方式。其中
 *set用于设置Matrix中的值。
 *pre是先乘，因为矩阵的乘法不满足交换律，因此先乘、后乘必须要严格区分。先乘相当于矩阵运算中的右乘。
 *post是后乘，因为矩阵的乘法不满足交换律，因此先乘、后乘必须要严格区分。后乘相当于矩阵运算中的左乘。
 *
 */
class CanvasMatrix : View {

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
        mCoo = Point(300, 300)
        mGridPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        invalidate()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.save()
        val matrix = Matrix()
        matrix.postRotate(45f)
//        matrix.postRotate()
//        matrix.postScale()
//        matrix.postSkew() 和canvas的变换类似
//        drawGrid(canvas, mWinSize, mGridPaint)
        canvas?.concat(matrix)
//        canvas?.matrix=matrix,兼容性不好
        drawCoo(canvas!!, mCoo!!, mWinSize!!, mGridPaint!!)
        canvas.restore()


        //使用 Matrix 来做自定义变换

        canvas?.save()
//        val matrix2 = Matrix()
        matrix.reset()
        //left top  right top  left bottom  right bottom
        //left top  left bottom  right bottom  right top

        //坐标点只要是按照一定正序或者逆序即可

        //src和dst两个数组控制4个顶点的坐标，
        // srcIndex,dstIndex分别是src和dst的第一个值的角标，
        // pointCount是4个顶点中要使用的个数，最大为4,0表示不进行操作变换
        matrix.setPolyToPoly(
            floatArrayOf(0f, 0f, 0f, 100f, 100f, 100f, 100f, 0f),
            0,
            floatArrayOf(20f, 0f, 0f, 100f, 100f, 100f, 80f, 0f),
            0,
            4
        )
        canvas?.concat(matrix)
//        canvas?.matrix=matrix,兼容性不好
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.wx)
        canvas.drawBitmap(bitmap, null, Rect(0, 0, 100, 100), mGridPaint)

        canvas.restore()

    }


    /**
     * 获取屏幕尺寸的大小，赋值给winSize的坐标点
     */
    fun loadWinSize(context: Context, winSize: Point?) {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()

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

    fun drawCoo(canvas: Canvas, coo: Point, winSize: Point, paint: Paint) {
        paint.strokeWidth = 4f
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE

        //设置虚线效果的floatArrayOf(可见长度，不可见长度)，偏移量
        paint.pathEffect = null

        //绘制直线
        canvas.drawPath(cooPath(coo, winSize), paint)


        //左箭头
        canvas.drawLine(
            winSize.x.toFloat(), coo.y.toFloat()
            , winSize.x.toFloat() - 40,
            coo.y.toFloat() - 20, paint
        )
        canvas.drawLine(
            winSize.x.toFloat(), coo.y.toFloat()
            , winSize.x.toFloat() - 40,
            coo.y.toFloat() + 20, paint
        )

        //下箭头
        canvas.drawLine(
            coo.x.toFloat(), winSize.y.toFloat()
            , coo.x.toFloat() - 20,
            winSize.y.toFloat() - 40, paint
        )
        canvas.drawLine(
            coo.x.toFloat(), winSize.y.toFloat()
            , coo.x.toFloat() + 20,
            winSize.y.toFloat() - 40, paint
        )

        //为坐标系绘制文字
        drawText4Coo(canvas, coo, winSize, paint)


    }

    /**
     * 坐标系路径绘制
     * @param coo:坐标原点
     * @param winSize:屏幕右下点
     */
    fun cooPath(coo: Point, winSize: Point): Path {
        val path = Path()
        //x正半轴线
//        path.moveTo(coo.x.toFloat(), coo.y.toFloat())
//        path.lineTo(winSize.x.toFloat(), coo.y.toFloat())
//        //x负半轴
//        path.moveTo(coo.x.toFloat(), coo.y.toFloat())
//        path.lineTo(coo.x.toFloat() - winSize.x, coo.y.toFloat())
//
//        //y正半轴线
//        path.moveTo(coo.x.toFloat(), coo.y.toFloat())
//        path.lineTo(coo.x.toFloat(), winSize.y.toFloat())
//        //y负半轴
//        path.moveTo(coo.x.toFloat(), coo.y.toFloat())
//        path.lineTo(coo.x.toFloat(), coo.y.toFloat() - winSize.y.toFloat())

        path.moveTo(winSize.x.toFloat(), coo.y.toFloat())
        path.lineTo(0f, coo.y.toFloat())

        path.moveTo(coo.x.toFloat(), winSize.y.toFloat())
        path.lineTo(coo.x.toFloat(), 0f)

        return path

    }


    fun drawText4Coo(canvas: Canvas, coo: Point, winSize: Point, paint: Paint) {
        //绘制文字 x ,y
        paint.textSize = 50f
        canvas.drawText("x", winSize.x - 60.toFloat(), coo.y - 40.toFloat(), paint)
        canvas.drawText("y", coo.x - 40.toFloat(), winSize.y - 60.toFloat(), paint)

        //绘制坐标系刻度
        paint.textSize = 25f

        //x正轴文字
        for (i in 1..(winSize.x - coo.x) / 50) {
            paint.strokeWidth = 2f
            canvas.drawText((100 * i).toString(), (coo.x - 20 + 100 * i).toFloat(), coo.y + 40.toFloat(), paint)
            paint.strokeWidth = 5f
            canvas.drawLine(
                (coo.x + 100 * i).toFloat(),
                coo.y.toFloat(),
                (coo.x + 100 * i).toFloat(),
                (coo.y - 10).toFloat(),
                paint
            )
        }
        //x负轴文字
        for (i in 1..(coo.x / 50)) {
            paint.strokeWidth = 2f
            canvas.drawText((-100 * i).toString(), (coo.x - 20 - 100 * i).toFloat(), coo.y + 40.toFloat(), paint)
            paint.strokeWidth = 5f
            canvas.drawLine(
                (coo.x - 100 * i).toFloat(),
                coo.y.toFloat(),
                (coo.x - 100 * i).toFloat(),
                (coo.y - 10).toFloat(),
                paint
            )
        }


        //y正轴文字
        for (i in 1..(winSize.y - coo.y) / 50) {
            paint.strokeWidth = 2f
            canvas.drawText((100 * i).toString(), (coo.x + 20).toFloat(), (coo.y + 10 + 100 * i).toFloat(), paint)
            paint.strokeWidth = 5f
            canvas.drawLine(
                (coo.x).toFloat(),
                (coo.y + 100 * i).toFloat(),
                (coo.x + 10).toFloat(),
                (coo.y + 100 * i).toFloat(),
                paint
            )
        }
        //y负轴文字
        for (i in 1..(coo.y) / 50) {
            paint.strokeWidth = 2f
            canvas.drawText((-100 * i).toString(), (coo.x + 20).toFloat(), (coo.y + 10 - 100 * i).toFloat(), paint)
            paint.strokeWidth = 5f
            canvas.drawLine(
                (coo.x).toFloat(),
                (coo.y - 100 * i).toFloat(),
                (coo.x + 10).toFloat(),
                (coo.y - 100 * i).toFloat(),
                paint
            )
        }

    }
}