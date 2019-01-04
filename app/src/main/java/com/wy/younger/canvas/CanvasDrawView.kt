package com.wy.younger.canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.wy.younger.R

/**
 * canvas的学习，canvas可以画颜色，点，线，各种形状的图形，文字，图片等
 *
 * 主要是绘制了一个坐标系，然后是个各种点，线，面的绘制
 *
 */
class CanvasDrawView : View {

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

        drawGrid(canvas, mWinSize, mGridPaint)
        drawCoo(canvas!!, mCoo!!, mWinSize!!, mGridPaint!!)


        /**
         * canvas的相关api
         */

        drawPoint(canvas!!, mGridPaint!!)
        drawLine(canvas!!, mGridPaint!!)
        drawPath(canvas!!, mGridPaint!!)
        drawRectCircle(canvas!!, mGridPaint!!)
        drawArc(canvas!!, mGridPaint!!)
        drawText(canvas!!, mGridPaint!!)
        drawBitmap(canvas!!, mGridPaint!!)

    }


    private fun drawPoint(canvas: Canvas, paint: Paint) {
        paint.color = Color.YELLOW
        canvas.drawPoint(400f, 400f, paint)//绘制一个点
        paint.color = Color.WHITE
        canvas.drawPoints(floatArrayOf(450f, 450f, 500f, 500f), paint)//绘制两个点 ，x1，y1，x2，y2。。。
        paint.color = Color.BLACK
        //offset表示跳过数组的几个值，比如1 表示跳过550
        //count表示offset后的几个值进行绘制，必须是偶数，这样才能成对绘制出点
        canvas.drawPoints(floatArrayOf(550f, 450f, 600f, 450f, 650f, 450f, 700f, 450f), 1, 6, paint)
    }

    /**
     * 绘制线，三个重载方法，意义和drawPoint类似
     * 但是一般更更多还是借助path进行绘制线
     */
    private fun drawLine(canvas: Canvas, paint: Paint) {
        paint.color = Color.RED
        paint.strokeWidth = 5f
        canvas.drawLine(300f, 300f, 400f, 500f, paint)
        canvas.drawLines(floatArrayOf(300f, 300f, 400f, 500f, 300f, 300f, 600f, 500f), paint)
        canvas.drawLines(
            floatArrayOf(300f, 300f, 400f, 500f, 300f, 300f, 600f, 500f, 300f, 300f, 700f, 500f),
            0,
            12,
            paint
        )
    }

    /**
     * path绘制，path可以绘制更加丰富的线条，需要单独讲
     */
    private fun drawPath(canvas: Canvas, paint: Paint) {
        paint.color = Color.GREEN
        paint.style = Paint.Style.FILL_AND_STROKE
        val path = Path()
        path.moveTo(550f, 550f)//移动到某一点
        path.lineTo(550f, 600f)//起始点到该点连接
//        path.lineTo(600f,600f)
        path.rLineTo(50f, 0f)//相对上个点的坐标的距离
        path.lineTo(600f, 550f)
        path.close()//与最初的点进行连接
        canvas.drawPath(path, paint)
    }


    /**
     * 绘制矩形和圆
     */
    private fun drawRectCircle(canvas: Canvas, paint: Paint) {

        paint.color = Color.YELLOW
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 2f
        canvas.drawRect(0f, 0f, 150f, 150f, paint)//左上角和右下角坐标
        canvas.drawRect(Rect(50, 50, 200, 200), paint)//一个区域范围对象，int型参数
        canvas.drawRect(RectF(80f, 80f, 230f, 230f), paint)//一个区域范围对象，float型参数

        canvas.drawRoundRect(RectF(100f, 100f, 250f, 250f), 10f, 10f, paint)//左上角和右下角坐标，x，y的圆弧角度，0就是矩形的绘制

        canvas.drawCircle(600f, 600f, 100f, paint)//绘制圆

    }


    /**
     * 绘制圆弧
     */
    private fun drawArc(canvas: Canvas, paint: Paint) {
        paint.color = Color.CYAN
        paint.style = Paint.Style.FILL_AND_STROKE
        //startAngle 0f表示y轴正方向  userCenter表示是否和中心点连接
        canvas.drawArc(RectF(700f, 700f, 900f, 900f), 0f, 180f, false, paint)
        paint.style = Paint.Style.STROKE
        canvas.drawArc(RectF(900f, 900f, 1000f, 1000f), 90f, 90f, true, paint)
    }

    /**
     * 文字绘制需要单独讲
     */
    private fun drawText(canvas: Canvas, paint: Paint) {
        paint.style = Paint.Style.FILL_AND_STROKE
        //文字   绘制文字index起始位置   index终止位置   坐标开始x   坐标结束y  paint
        canvas.drawText("wy", 0, 2, 100f, 600f, paint)
    }

    private fun drawBitmap(canvas: Canvas, paint: Paint) {

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.wx)
        canvas.drawBitmap(bitmap, 0f, 300f, paint)

        //不对图片进行剪裁，全部显示
        canvas.drawBitmap(bitmap, null, RectF(0f, 700f, 100f, 800f), paint)

        Log.i("gadfdfsfsdfa", bitmap.width.toString() + "      " + bitmap.height)

        //对图片进行剪裁，只显示部分大小
        canvas.drawBitmap(bitmap, Rect(0, 0, bitmap.width / 2, bitmap.height / 2), Rect(0, 1000, 100, 1100), paint)

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