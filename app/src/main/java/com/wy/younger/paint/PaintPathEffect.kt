package com.wy.younger.paint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Paint 线条形状
 *
 * PathEffect 来给图形的轮廓设置效果。对 Canvas 所有的图形绘制有效，也就是 drawLine() drawCircle() drawPath() 这些方法
 *
 * Android 中的 6 种 PathEffect。PathEffect 分为两类，
 * 单一效果的 CornerPathEffect DiscretePathEffect DashPathEffect PathDashPathEffect
 * ，和组合效果的 SumPathEffect ComposePathEffect
 *
 * CornerPathEffect:参数是圆的半径  使得线更加圆滑
 *
 * DiscretePathEffect(segmentLength,deviation):segmentLength 是用来拼接的每个线段的长度， deviation 是偏离量
 *
 * DashPathEffect(float[] intervals, float phase) 中，
 * 第一个参数 intervals 是一个数组，它指定了虚线的格式：数组中元素必须为偶数（最少是 2 个），
 * 按照「画线长度、空白长度、画线长度、空白长度」……的顺序排列，例如上面代码中的 20, 5, 10, 5
 * 就表示虚线是按照「画 20 像素、空 5 像素、画 10 像素、空 5 像素」的模式来绘制；
 * 第二个参数 phase 是虚线的偏移量
 *
 *
 *PathDashPathEffect(Path shape, float advance, float phase, PathDashPathEffect.Style style) 中，
 *  shape 参数是用来绘制的 Path ；
 *  advance 是两个相邻的 shape 段之间的间隔，
 *  不过注意，这个间隔是两个 shape 段的起点的间隔，而不是前一个的终点和后一个的起点的距离；
 *  phase 和 DashPathEffect 中一样，是虚线的偏移；
 *  最后一个参数 style，是用来指定拐弯改变的时候 shape 的转换方式。style 的类型为 PathDashPathEffect.Style ，
 *  是一个 enum ，具体有三个值：
 *  TRANSLATE：位移
 *  ROTATE：旋转
 *  MORPH：变体
 *
 *SumPathEffect:分别按照两种 PathEffect 分别对目标进行绘制
 *
 * ComposePathEffect:这也是一个组合效果类的 PathEffect 。不过它是先对目标 Path 使用一个 PathEffect，然后再对这个改变后的 Path 使用另一个 PathEffect
 *
 */
class PaintPathEffect : View {
    var paint: Paint? = null
    var path: Path? = null
    var path2: Path? = null
    var dashPath: Path? = null

    var cornerPathEffect: CornerPathEffect? = null
    var discretePathEffect: DiscretePathEffect? = null
    var dashPathEffect: DashPathEffect? = null
    var pathDashPathEffect: PathDashPathEffect? = null
    var sumPathEffect: SumPathEffect? = null
    var cmposePathEffect: ComposePathEffect? = null

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
        paint?.strokeWidth = 5f
        path = Path()
        path2 = Path()
        dashPath = Path()


        dashPath?.moveTo(0f, 20f)
        dashPath?.rLineTo(10f, -20f)
        dashPath?.rLineTo(10f, 20f)
        dashPath?.close()

        /**
         * Canvas.drawLine() 和 Canvas.drawLines() 方法画直线时，setPathEffect() 是不支持硬件加速的；
         *  PathDashPathEffect 对硬件加速的支持也有问题，所以当使用 PathDashPathEffect 的时候，最好也把硬件加速关了。
         */
        cornerPathEffect = CornerPathEffect(20f)
        discretePathEffect = DiscretePathEffect(20f, 10f)
        dashPathEffect = DashPathEffect(floatArrayOf(10f, 5f, 20f, 5f), 10f)
        pathDashPathEffect = PathDashPathEffect(dashPath, 40f, 0f, PathDashPathEffect.Style.MORPH)


        sumPathEffect = SumPathEffect(cornerPathEffect, discretePathEffect)

        cmposePathEffect = ComposePathEffect(cornerPathEffect, discretePathEffect)


        path?.moveTo(0f, 0f)
        path?.rLineTo(100f, 200f)
        path?.rLineTo(200f, -100f)
        path?.rLineTo(200f, 200f)
        path?.rLineTo(200f, -300f)
        path?.rLineTo(200f, 200f)

        path2?.moveTo(0f, 50f)
        path2?.rLineTo(100f, 200f)
        path2?.rLineTo(200f, -100f)
        path2?.rLineTo(200f, 200f)
        path2?.rLineTo(200f, -300f)
        path2?.rLineTo(200f, 200f)

        paint?.setShadowLayer(10f, 0f, 0f, Color.RED)//会给所有draw加一个阴影效果

        invalidate()

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        canvas?.save()


        canvas?.drawPath(path, paint)

        paint?.pathEffect = cornerPathEffect
        canvas?.drawPath(path2, paint)



        canvas?.translate(0f, 400f)
        paint?.pathEffect = null
        canvas?.drawPath(path, paint)

        paint?.pathEffect = discretePathEffect
        canvas?.drawPath(path2, paint)


        canvas?.translate(0f, 400f)
        paint?.pathEffect = null
        canvas?.drawPath(path, paint)

//        setLayerType(LAYER_TYPE_SOFTWARE, paint)//相同的shader必须要设置不使用硬件加速才会生效
        paint?.pathEffect = dashPathEffect
        canvas?.drawPath(path2, paint)


        canvas?.translate(0f, 400f)
        paint?.pathEffect = null
        canvas?.drawPath(path, paint)

        paint?.pathEffect = pathDashPathEffect
        canvas?.drawPath(path2, paint)

        canvas?.translate(0f, 400f)
        paint?.pathEffect = null
        canvas?.drawPath(path, paint)

        paint?.pathEffect = sumPathEffect
        canvas?.drawPath(path2, paint)

        canvas?.translate(0f, 400f)
        paint?.pathEffect = null
        canvas?.drawPath(path, paint)

        paint?.pathEffect = cmposePathEffect
        canvas?.drawPath(path2, paint)

        canvas?.restore()

    }
}