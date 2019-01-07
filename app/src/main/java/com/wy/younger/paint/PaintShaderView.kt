package com.wy.younger.paint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.wy.younger.R

/**
 * Paint作为画笔，它的各种api决定着画出来的图的各种样式
 */
class PaintShaderView : View {
    var paint: Paint? = null

    var linerGradient1: LinearGradient? = null
    var linerGradient2: LinearGradient? = null
    var linerGradient3: LinearGradient? = null

    var radialGradient1: RadialGradient? = null
    var radialGradient2: RadialGradient? = null
    var radialGradient3: RadialGradient? = null

    var sweepGradient1: SweepGradient? = null

    var bitmapShader: BitmapShader? = null

    var bitmapWidth = 0f
    var bitmapHeight = 0f

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

        /**
         * 参数：
         * x0 y0 x1 y1：渐变的两个端点的位置
         * color0 color1 是端点的颜色
         * tile：端点范围之外的着色规则，
         * 类型是 TileMode。TileMode 一共有 3 个值可选：
         * CLAMP, MIRROR 和  REPEAT。CLAMP 会在端点之外延续端点处的颜色；
         * MIRROR 是镜像模式；REPEAT 是重复模式。具体的看一下例子就明白。
         *
         * gradient的坐标限定了现实的内容，draw的坐标限定了现实范围，内容是根据TileMode决定的
         */
        linerGradient1 = LinearGradient(0f, 0f, 200f, 0f, Color.RED, Color.YELLOW, Shader.TileMode.CLAMP)
        linerGradient2 = LinearGradient(200f, 0f, 400f, 0f, Color.RED, Color.YELLOW, Shader.TileMode.MIRROR)
        linerGradient3 = LinearGradient(400f, 0f, 600f, 0f, Color.RED, Color.YELLOW, Shader.TileMode.REPEAT)

        /**
         * 沿着中点点渐变的
         *
         * gradient的坐标限定了现实的内容，draw的坐标限定了现实范围，内容是根据TileMode决定的
         */
        radialGradient1 = RadialGradient(300f, 500f, 100f, Color.RED, Color.YELLOW, Shader.TileMode.CLAMP)
        radialGradient2 = RadialGradient(300f, 500f, 100f, Color.RED, Color.YELLOW, Shader.TileMode.MIRROR)
        radialGradient3 = RadialGradient(300f, 500f, 100f, Color.RED, Color.YELLOW, Shader.TileMode.REPEAT)


        /**
         * 扫描渐变,draw的原点坐标需要和SweepGradient的原点坐标一致才可以
         */
        sweepGradient1 = SweepGradient(300f, 1100f, Color.RED, Color.YELLOW)

        /**
         * bitmapshader 默认是坐标点0，0进行绘制的
         */
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.wx)
        bitmapShader = BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.REPEAT)
        bitmapWidth = bitmap.width.toFloat()
        bitmapHeight = bitmap.height.toFloat()

        invalidate()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        /**
         * 应该注意到：
         */
        paint?.shader = linerGradient2
        canvas?.drawRect(RectF(0f, 0f, 200f, 200f), paint)

        canvas?.drawRect(RectF(200f, 0f, 400f, 200f), paint)

        canvas?.drawRect(RectF(400f, 0f, 600f, 200f), paint)


        paint?.shader = radialGradient3

        canvas?.drawCircle(300f, 500f, 100f, paint)
        canvas?.drawCircle(300f, 500f, 200f, paint)
        canvas?.drawCircle(300f, 500f, 300f, paint)

        paint?.shader = sweepGradient1
//        canvas?.drawCircle(300f, 1000f, 100f, paint)
        canvas?.drawCircle(300f, 1100f, 300f, paint)

        paint?.shader = bitmapShader
        Log.i("gfdsfdsfsdfa", bitmapWidth.toString() + "      " + bitmapHeight)

//        canvas?.drawCircle(bitmapWidth/2, bitmapHeight/2, 60f, paint)
        canvas?.drawRect(Rect(0, 0, 1000, 600), paint)


    }
}