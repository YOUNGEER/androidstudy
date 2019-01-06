package com.wy.younger.paint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * PaintPorterduffMode
 */
class PaintPorterduffMode : View {
    var paint: Paint? = null

    var linearGradient1: LinearGradient? = null
    var linearGradient2: LinearGradient? = null

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
        paint?.color = Color.YELLOW

        linearGradient1 = LinearGradient(0f, 0f, 100f, 100f, Color.RED, Color.RED, Shader.TileMode.CLAMP)
        linearGradient2 = LinearGradient(50f, 50f, 150f, 150f, Color.GREEN, Color.GREEN, Shader.TileMode.CLAMP)

        /**
         * 注意：
         * shader1：dst
         * shader2：src
         *
         * 说实话，shader的tileMode感觉有点鸡肋，比如对于linearGradient，无论设置成哪种模式，canvas的绘制内容区域都会被其颜色延伸到
         * composeShader此时测试都十分不直观，因为它们重叠区域是canvas绘制的所有内容，除非是BitmapGradient，并且图片边缘是无颜色的占位像素
         *
         */

        //官方文档：https://developer.android.com/reference/android/graphics/PorterDuff.Mode

//        composeShader = ComposeShader(linearGradient1, linearGradient2, PorterDuff.Mode.SRC)//green

//        composeShader = ComposeShader(linearGradient1, linearGradient2, PorterDuff.Mode.SRC_OVER)//green

//        composeShader = ComposeShader(linearGradient1, linearGradient2, PorterDuff.Mode.SRC_IN)//green

//        composeShader = ComposeShader(linearGradient1, linearGradient2, PorterDuff.Mode.SRC_OUT)//none

//        composeShader = ComposeShader(linearGradient1, linearGradient2, PorterDuff.Mode.SRC_ATOP)//green


//        composeShader = ComposeShader(linearGradient1, linearGradient2, PorterDuff.Mode.DST)//red

//        composeShader = ComposeShader(linearGradient1, linearGradient2, PorterDuff.Mode.DST_OVER)//red

//        composeShader = ComposeShader(linearGradient1, linearGradient2, PorterDuff.Mode.DST_IN)//red

//        composeShader = ComposeShader(linearGradient1, linearGradient2, PorterDuff.Mode.DST_OUT)//none

//        composeShader = ComposeShader(linearGradient1, linearGradient2, PorterDuff.Mode.DST_ATOP)//red

//        composeShader = ComposeShader(linearGradient1, linearGradient2, PorterDuff.Mode.CLEAR)//none

//        composeShader = ComposeShader(linearGradient1, linearGradient2, PorterDuff.Mode.XOR)//none


        //官方文档：https://developer.android.com/reference/android/graphics/PorterDuff.Mode
//        composeShader = ComposeShader(linearGradient1, linearGradient2, PorterDuff.Mode.DARKEN)//重叠部分混合色偏黑

//        composeShader = ComposeShader(linearGradient1, linearGradient2, PorterDuff.Mode.LIGHTEN)//重叠部分混合色混合色偏亮

//        composeShader = ComposeShader(linearGradient1, linearGradient2, PorterDuff.Mode.MULTIPLY)//重叠部分混合色

//        composeShader = ComposeShader(linearGradient1, linearGradient2, PorterDuff.Mode.SCREEN)//重叠部分混合色

        composeShader = ComposeShader(linearGradient1, linearGradient2, PorterDuff.Mode.OVERLAY)//重叠部分混合色


        setLayerType(LAYER_TYPE_SOFTWARE, null)//相同的shader必须要设置不使用硬件加速才会生效
        invalidate()
    }


    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)

        paint?.shader = composeShader
        canvas?.drawRect(0f, 0f, 200f, 200f, paint)

    }
}