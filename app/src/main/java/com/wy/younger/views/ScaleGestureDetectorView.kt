package com.wy.younger.views

import android.content.Context
import android.graphics.*
import android.support.annotation.IntDef
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import com.wy.younger.R
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 *@package:com.wy.younger.views
 *@data on:2019/1/14 14:33
 *author:YOUNG
 *desc:TODO
 *
 *
 * 在缩放手势中我们其实主要关心的只有两个参数而已，
 * 一个是缩放的中心点，另一个就是缩放比例了
 */
class ScaleGestureDetectorView : View {


    var scaleGestureDetector: ScaleGestureDetector? = null

    var gestureDetector: GestureDetector? = null

    // 画布当前的 Matrix， 用于获取当前画布的一些状态信息，例如缩放大小，平移距离等
    var canvasMatrix: Matrix? = null

    // 将用户触摸的坐标转换为画布上坐标所需的 Matrix， 以便找到正确的缩放中心位置
    var invertMatrix: Matrix? = null

    // 所有用户触发的缩放、平移等操作都通过下面的 Matrix 直接作用于画布上，
    // 将系统计算的一些初始缩放平移信息与用户操作的信息进行隔离，让操作更加直观
    var userMatrix: Matrix? = null

    var bitmap: Bitmap? = null

    internal var mPaint: Paint? = null


    // 基础的缩放和平移信息，该信息与用户的手势操作无关

    internal var mBaseScale: Float = 0.toFloat()
    internal var mBaseTranslateX: Float = 0.toFloat()
    internal var mBaseTranslateY: Float = 0.toFloat()


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

    private fun initView(context: Context) {

        mPaint = Paint()
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.test)

        canvasMatrix = Matrix()
        invertMatrix = Matrix()
        userMatrix = Matrix()

        gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent?): Boolean {
                if (!userMatrix!!.isIdentity) {
                    userMatrix?.reset()
                } else {
                    val points = mapPoint(e!!.x, e!!.y, invertMatrix!!)
                    userMatrix!!.postScale(MAX_SCALE, MAX_SCALE, points[0], points[1])
                }
                fixTranslate()
                invalidate()
                return true
            }

            override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
                val scale = getMatrixValue(MSCALE_X, canvasMatrix!!)
                userMatrix?.preTranslate(-distanceX / scale, -distanceY / scale)
                //fixTranslate();   // 在用户滚动时不进行修正，保证用户滚动时也有响应， 在用户抬起手指后进行修正
                invalidate()
                return true

            }


        })
        scaleGestureDetector =
                ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
                    override fun onScale(detector: ScaleGestureDetector?): Boolean {
                        var scaleFactor = detector?.scaleFactor
                        val fx = detector?.focusX
                        val fy = detector?.focusY
                        val points = mapPoint(fx!!, fy!!, invertMatrix!!)
                        scaleFactor = getRealScaleFactor(scaleFactor!!)
                        userMatrix?.preScale(scaleFactor, scaleFactor, points[0], points[1])
                        fixTranslate()
                        invalidate()
                        return true
                    }
                })
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector?.onTouchEvent(event)
        gestureDetector?.onTouchEvent(event)
        if (event?.actionMasked == MotionEvent.ACTION_UP) {
            fixTranslate()
        }
        return true
    }


    // 修正缩放
    private fun fixTranslate() {
        // 对 Matrix 进行预计算，并根据计算结果进行修正
        val viewMatrix = matrix    // 获取当前控件的Matrix
        viewMatrix.preTranslate(mBaseTranslateX, mBaseTranslateY)
        viewMatrix.preScale(mBaseScale, mBaseScale)
        viewMatrix.preConcat(userMatrix)
        val invert = Matrix()
        viewMatrix.invert(invert)
        val rect = Rect()
        getGlobalVisibleRect(rect)

        val userScale = getMatrixValue(MSCALE_X, userMatrix!!)
        val scale = getMatrixValue(MSCALE_X, viewMatrix)

        val center = mapPoint(bitmap!!.width / 2.0f, bitmap!!.height / 2.0f, viewMatrix)
        val distanceX = center[0] - width / 2.0f
        val distanceY = center[1] - height / 2.0f
        val wh = mapVectors(bitmap!!.width.toFloat(), bitmap!!.height.toFloat(), viewMatrix)

        if (userScale <= 1.0f) {
            userMatrix!!.preTranslate(-distanceX / scale, -distanceY / scale)
        } else {
            val lefttop = mapPoint(0f, 0f, viewMatrix)
            val rightbottom = mapPoint(bitmap!!.width.toFloat(), bitmap!!.height.toFloat(), viewMatrix)

            // 如果宽度小于总宽度，则水平居中
            if (wh[0] < width) {
                userMatrix!!.preTranslate(distanceX / scale, 0f)
            } else {
                if (lefttop[0] > 0) {
                    userMatrix!!.preTranslate(-lefttop[0] / scale, 0f)
                } else if (rightbottom[0] < width) {
                    userMatrix!!.preTranslate((width - rightbottom[0]) / scale, 0f)
                }

            }
            // 如果高度小于总高度，则垂直居中
            if (wh[1] < height) {
                userMatrix!!.preTranslate(0f, -distanceY / scale)
            } else {
                if (lefttop[1] > 0) {
                    userMatrix!!.preTranslate(0f, -lefttop[1] / scale)
                } else if (rightbottom[1] < height) {
                    userMatrix!!.preTranslate(0f, (height - rightbottom[1]) / scale)
                }
            }
        }
        invalidate()
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (bitmap!!.width * 1.0f / bitmap!!.height > w * 1.0f / h) {
            mBaseScale = w * 1.0f / bitmap!!.width
            mBaseTranslateX = 0f
            mBaseTranslateY = (h - bitmap!!.height * mBaseScale) / 2
        } else {
            mBaseScale = h * 1.0f / bitmap!!.height * 1.0f
            mBaseTranslateX = (w - bitmap!!.width * mBaseScale) / 2
            mBaseTranslateY = 0f
        }

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint?.style = Paint.Style.STROKE
        mPaint?.strokeWidth = 6f
        canvas?.translate(mBaseTranslateX, mBaseTranslateY)
        canvas?.scale(mBaseScale, mBaseScale)

        canvas?.save()
        canvas?.concat(userMatrix)

        canvasMatrix = canvas?.matrix
        canvasMatrix?.invert(invertMatrix)

        canvas?.drawBitmap(bitmap, 0f, 0f, mPaint)
        canvas?.restore()

    }

    //--- 获取 Matrix 中的属性 ---
    val matrixValues = FloatArray(9)
    val MSCALE_X: Int = 0
    var MSKEW_X: Int = 1
    val MTRANS_X: Int = 2
    val MSKEW_Y: Int = 3
    val MSCALE_Y: Int = 4
    val MTRANS_Y: Int = 5
    val MPERSP_0: Int = 6
    val MPERSP_1: Int = 7

    val MPERSP_2: Int = 8

    @IntDef(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    @Retention(RetentionPolicy.SOURCE)
    annotation class MatrixName

    private fun getMatrixValue(@MatrixName name: Int, matrix: Matrix): Float {
        matrix.getValues(matrixValues)
//        MSCALE_X1+ MSKEW_X
        return matrixValues[name]
    }


    //--- 限制缩放比例 ---
    val MAX_SCALE = 4.0f    //最大缩放比例
    val MIN_SCALE = 0.5f    // 最小缩放比例

    private fun getRealScaleFactor(currentScaleFactor: Float): Float {
        var realScale = 1.0f
        val userScale = getMatrixValue(MSCALE_X, userMatrix!!)    // 用户当前的缩放比例
        val theoryScale = userScale * currentScaleFactor           // 理论缩放数值

        // 如果用户在执行放大操作并且理论缩放数据大于4.0
        realScale = if (currentScaleFactor > 1.0f && theoryScale > MAX_SCALE) {
            MAX_SCALE / userScale
        } else if (currentScaleFactor < 1.0f && theoryScale < MIN_SCALE) {
            MIN_SCALE / userScale
        } else {
            currentScaleFactor
        }
        return realScale
    }


    //--- 将坐标转换为画布坐标 ---
    private fun mapPoint(x: Float, y: Float, matrix: Matrix): FloatArray {
        val temp = FloatArray(2)
        temp[0] = x
        temp[1] = y
        matrix.mapPoints(temp)
        return temp
    }

    private fun mapVectors(x: Float, y: Float, matrix: Matrix): FloatArray {
        val temp = FloatArray(2)
        temp[0] = x
        temp[1] = y
        matrix.mapVectors(temp)
        return temp
    }

}