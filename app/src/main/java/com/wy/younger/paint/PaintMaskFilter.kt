package com.wy.younger.paint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wy.younger.R

/**
 * MaskFilter ：设置 绘制层上方的附加效果。
 * MaskFilter 有两种： BlurMaskFilter 和 EmbossMaskFilter
 *
 *
 *
 * BlurMaskFilter的构造方法 BlurMaskFilter(float radius, BlurMaskFilter.Blur style) 中，
 * radius 参数是模糊的范围，  style 是模糊的类型。一共有四种：
 * NORMAL: 内外都模糊绘制
 * SOLID: 内部正常绘制，外部模糊
 * INNER: 内部模糊，外部不绘制
 * OUTER: 内部不绘制，外部模糊
 *
 *
 *
 * EmbossMaskFilter:浮雕效果的 MaskFilter。  deprecated,建议不用了
 */
class PaintMaskFilter : View {
    var paint: Paint? = null

    var blurMaskFilter: BlurMaskFilter? = null
    var embossMaskFilter: EmbossMaskFilter? = null

    var bitmap: Bitmap? = null


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

        bitmap = BitmapFactory.decodeResource(resources, R.drawable.pic1)

        bitmap = Bitmap.createScaledBitmap(bitmap, 600, 600, true)

        blurMaskFilter = BlurMaskFilter(20f, BlurMaskFilter.Blur.NORMAL)



        invalidate()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.save()
        canvas?.drawBitmap(bitmap, 0f, 0f, paint)

        canvas?.translate(0f, 600f)
        paint?.maskFilter = blurMaskFilter
        canvas?.drawBitmap(bitmap, 0f, 0f, paint)

        blurMaskFilter = BlurMaskFilter(20f, BlurMaskFilter.Blur.SOLID)
        canvas?.translate(0f, 600f)
        paint?.maskFilter = blurMaskFilter
        canvas?.drawBitmap(bitmap, 0f, 0f, paint)

        blurMaskFilter = BlurMaskFilter(20f, BlurMaskFilter.Blur.INNER)
        canvas?.translate(0f, 600f)
        paint?.maskFilter = blurMaskFilter
        canvas?.drawBitmap(bitmap, 0f, 0f, paint)


        blurMaskFilter = BlurMaskFilter(20f, BlurMaskFilter.Blur.OUTER)
        canvas?.translate(0f, 600f)
        paint?.maskFilter = blurMaskFilter
        canvas?.drawBitmap(bitmap, 0f, 0f, paint)


    }
}