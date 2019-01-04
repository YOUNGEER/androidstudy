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
 * 主要是学习下canvas的变换
 *
 * 1.canvas是什么？
 * 可以理解为没有边界的一层透明的图层，一张白纸，可以在上面进行任何的绘制，如果在屏幕显示的范围进行了绘制
 * 那么我们就能看到这些内容
 *
 * 重点讲下canvas.save()和canvas.restore()
 * canvas可以理解为不同的图层，save表示保存当前图层，重新生成一个新的图层，该图片和原图片的绘制没有任何的关系
 * resotre表示将新的当前的图层和下面的图层进行合并
 *
 *
 *
 * canvas一般可以做常见的二维变换   translate   rotate    scale   skew
 * matrix可以做常见和不常见的二维变换
 * camera可以做三维变换
 */
class CanvasTranslation : View {

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
        invalidate()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

//        val count = canvas.getSaveCount()//获取图层的个数3
//        canvas.restoreToCount(1)//直接恢复到第几个图层

//        public int save (int saveFlags)
//        默认：MATRIX_SAVE_FLAG | CLIP_SAVE_FLAG

//        ALL_SAVE_FLAG	            保存全部状态
//        CLIP_SAVE_FLAG	            过期---仅保存剪辑区(不作为图层)
//        CLIP_TO_LAYER_SAVE_FLAG	    过期---仅剪裁区作为图层保存
//        FULL_COLOR_LAYER_SAVE_FLAG	过期---仅保存图层的全部色彩通道
//        HAS_ALPHA_LAYER_SAVE_FLAG	过期---仅保存图层的alpha(不透明度)通道 MATRIX_SAVE_FLAG
//        过期---仅保存Matrix信息( translate, rotate, scale, skew)


        drawGrid(canvas, mWinSize, mGridPaint)
        drawCoo(canvas!!, mCoo!!, mWinSize!!, mGridPaint!!)

        mGridPaint?.color = Color.GREEN
        canvas.drawRect(mCoo!!.x + 100f, mCoo!!.x + 100f, mCoo!!.y + 300f, mCoo!!.y + 200f, mGridPaint)

        //将canvas旋转45度，rect此时会是一个45度的区域大小
        canvas.save()//保存canvas状态
        //(角度,中心点x,中心点y)
        canvas.rotate(45f, mCoo!!.x + 100f, mCoo!!.y + 100f)
        mGridPaint?.color = Color.RED
        canvas.drawRect(mCoo!!.x + 100f, mCoo!!.x + 100f, mCoo!!.y + 300f, mCoo!!.y + 200f, mGridPaint)
        canvas.restore()//图层向下合并

        //平移想轴正方向100大小，此时的rect的0就是原先的100
        canvas?.save()
        canvas.translate(100f, 0f)
        mGridPaint?.color = Color.YELLOW
        canvas.drawRect(0f, 0f, 100f, 100f, mGridPaint)
        canvas?.restore()

        //此时canvas并不是一个正方向了
        canvas?.save()
        canvas.scale(0.5f, 1f)
        canvas.drawRect(0f, 0f, 100f, 100f, mGridPaint)
        canvas?.restore()

        canvas?.save()
        canvas.skew(0.2f, 0f)//x,y的错切系数，错切的意思是图片拉伸，但是面积保持不变
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.wx)
        canvas.drawBitmap(bitmap, 0f, 300f, mGridPaint)
        canvas?.restore()


        canvas?.save()
        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.wx)
        canvas.clipRect(Rect(0, 0, bitmap2.width / 2, bitmap2.height / 2))
        canvas.drawBitmap(bitmap2, 0f, 800f, mGridPaint)
        canvas?.restore()


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