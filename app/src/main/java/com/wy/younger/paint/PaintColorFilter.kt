package com.wy.younger.paint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wy.younger.R

/**
 * Paint Colorfilter
 * paint是设置颜色的第二重境界
 * 用来基于颜色进行过滤处理
 *
 *
 * 使用的是 Paint.setColorFilter(ColorFilter filter) 方法。
 * ColorFilter 并不直接使用，而是使用它的子类。它共有三个子类：
 * LightingColorFilter PorterDuffColorFilter 和  ColorMatrixColorFilter
 *
 * LightingColorFilter（mul,add）
 * R' = R * mul.R / 0xff + add.R
 * G' = G * mul.G / 0xff + add.G
 * B' = B * mul.B / 0xff + add.B
 *
 * 这个计算过程是：三原色拆分计算  比如mul.R=00  ,add.R=0 ,那么红色就会被过滤了
 *
 *
 * PorterDuffColorFilter(int,mode)
 * 这个 PorterDuffColorFilter 的作用是使用一个指定的颜色
 * 和一种指定的 PorterDuff.Mode 来与绘制对象进行合成
 * int:颜色值   对应这src     bitmap对应这dst
 * mode：porterduff的mode
 *
 *
 *
 * ColorMatrixColorFilter
 * ColorMatrixColorFilter 使用一个 ColorMatrix
 * 来对颜色进行处理。 ColorMatrix 这个类，内部是一个 4x5 的矩阵：
 *  [ a, b, c, d, e,
 *   f, g, h, i, j,
 *  k, l, m, n, o,
 *  p, q, r, s, t ]
 *
 *
 *  计算过程：
 *  R’ = a*R + b*G + c*B + d*A + e;
 *  G’ = f*R + g*G + h*B + i*A + j;
 *  B’ = k*R + l*G + m*B + n*A + o;
 *  A’ = p*R + q*G + r*B + s*A + t;
 *
 * 可以发现，相对LightingColorFilter，颜色的变化更加细微，可以进行精确控制，但是这个需要一定的算法基础，
 *
 */
class PaintColorFilter : View {
    var paint: Paint? = null
    var bitmap: Bitmap? = null
    var lightingColorFilter: LightingColorFilter? = null
    var porterDuffColorFilter: PorterDuffColorFilter? = null
    var colorMatrixColorFilter: ColorMatrixColorFilter? = null


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

        lightingColorFilter = LightingColorFilter(0x00ffff, 0x000000)

        porterDuffColorFilter = PorterDuffColorFilter(Color.RED, PorterDuff.Mode.LIGHTEN)

        val colorMatrix = ColorMatrix(
            floatArrayOf(
                1f,
                0f,
                0f,
                0f,
                125f,//偏红

                0f,
                1f,
                0f,
                0f,
                0f,

                0f,
                0f,
                1f,
                0f,
                0f,

                0f,
                0f,
                0f,
                1f,
                0f
            )
        )

        colorMatrixColorFilter = ColorMatrixColorFilter(colorMatrix)

        bitmap = BitmapFactory.decodeResource(resources, R.drawable.pic1)

        invalidate()
    }


    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)

        bitmap = Bitmap.createScaledBitmap(bitmap, 600, 600, true)
        canvas?.drawBitmap(bitmap, 0f, 0f, paint)

        paint?.colorFilter = lightingColorFilter
        canvas?.drawBitmap(bitmap, 0f, bitmap?.height!!.toFloat(), paint)

        paint?.colorFilter = porterDuffColorFilter
        canvas?.drawBitmap(bitmap, 0f, bitmap?.height!!.toFloat() * 2, paint)

        paint?.colorFilter = colorMatrixColorFilter
        canvas?.drawBitmap(bitmap, 0f, bitmap?.height!!.toFloat() * 3, paint)


    }
}