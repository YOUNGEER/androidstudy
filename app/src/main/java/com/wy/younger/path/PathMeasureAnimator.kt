package com.wy.younger.path

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * PathMeasure:可以获得path的任一点坐标
 *
 *
 * PathMeasure（path，forceclose），path，forceclose一般为true，没有path没有闭合，但是true计算出来的path的lenght会变大，但是不会进行绘制操作
 *
 *
 * pathMeasure.length：path的总的路径长度
 *
 *
 *  pathMeasure.getSegment(0f, lenght, pathDst, true)，截取0，到lenght之间的一段path，并赋路径到pathDst中，
 *                              startWithMoveTo一般为true，保证每次截取的Path片段都是正常的、完整的。
 *
 *
 * getPosTan：这个API用于获取路径上某点的坐标及其切线的坐标
 */
class PathMeasureAnimator : View {
    var path1: Path? = null
    var pathDst: Path? = null
    var pathDst2: Path? = null
    var paint: Paint? = null
    var paint2: Paint? = null
    var pathMeasure: PathMeasure? = null

    var length: Float = 0f

    var stop: Float = 0f
    var animatorValue: Float = 0f

    var dashPathEffect: DashPathEffect? = null
    var dashPathEffect2: DashPathEffect? = null


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
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint?.style = Paint.Style.STROKE
        paint?.color = Color.RED
        paint?.textSize = 30f
        paint2 = Paint(Paint.ANTI_ALIAS_FLAG)
        paint2?.style = Paint.Style.STROKE
        paint2?.color = Color.RED
        paint2?.textSize = 30f

        path1 = Path()
        pathDst = Path()
        pathDst2 = Path()


        path1?.addCircle(100f, 100f, 100f, Path.Direction.CW)

        pathMeasure = PathMeasure(path1, true)//创建 PathMeasure


        length = pathMeasure?.length!!//获取path的长度

        val animator: ValueAnimator = ValueAnimator.ofFloat(1f, 0f)

        animator.addUpdateListener {
            animatorValue = it.animatedValue as Float
            dashPathEffect = DashPathEffect(floatArrayOf(length, length), animatorValue * length)


            dashPathEffect2 = if (it.animatedValue as Float >= 0.5f) {
                DashPathEffect(floatArrayOf(length, length), animatorValue * length)
            } else {
                DashPathEffect(floatArrayOf(length, length), length - animatorValue * length * 2)
            }

            paint?.pathEffect = dashPathEffect
            paint2?.pathEffect = dashPathEffect2
            invalidate()
        }

        animator.duration = 3000
        animator.start()


        //，通过参数startD和stopD来控制截取的长度，并将截取的Path保存到dst中，
        // 最后一个参数startWithMoveTo表示起始点是否使用moveTo方法，
        // 通常为True，保证每次截取的Path片段都是正常的、完整的
//        pathMeasure?.getSegment()


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        stop = animatorValue * lenght
//        canvas?.translate(100f, 100f)
//        pathMeasure?.getSegment(0f, stop, pathDst, true)
//        canvas?.drawPath(pathDst, paint)
//        canvas?.drawText("圆长度：$lenght，目前绘制长度$stop", 300f, 150f, paint)
//
//
//        canvas?.translate(0f, 400f)
//        val start = (stop - (0.5f - Math.abs(animatorValue - 0.5).toFloat()) * lenght)
//        pathMeasure?.getSegment(start, stop, pathDst2, true)
//        canvas?.drawPath(pathDst2, paint)
//        canvas?.drawText("圆开始长度：$start，目前绘制长度$stop", 300f, 150f, paint)


        canvas?.drawPath(path1, paint)

        canvas?.translate(0f, 300f)
        canvas?.drawPath(path1, paint2)


    }

}