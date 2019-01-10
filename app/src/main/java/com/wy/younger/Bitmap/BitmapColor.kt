package com.wy.younger.Bitmap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wy.younger.R

/**
 *@package:com.wy.younger.canvas
 *@data on:2019/1/4 14:16
 *author:YOUNG
 *desc:TODO
 */

/**
 * 一张jpg或png图片就是很多颜色的合集，而这些合集信息都被封装到了Bitmap类中
 * 你可以使用Bitmap获取任意像素点，并修改它，对与某像素点而言，颜色信息是其主要的部分
 *
 *利用每个circle来复刻出了图片，因为图片像素比较大，所以去每4位取其中的一个颜色点作为circle的color
 *
 *
 *
 */
class BitmapColor : View {

    var bitmap: Bitmap? = null
    var paint: Paint? = null

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

        bitmap = BitmapFactory.decodeResource(resources, R.drawable.pic1)



        invalidate()

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        for (i in 0..(bitmap!!.width / 4 - 1)) {
            for (j in 0..(bitmap!!.height / 4 - 1)) {
                val color_0_0 = bitmap?.getPixel(i * 4, j * 4) as Int//获取坐标0，0点的像素颜色值
                paint?.color = color_0_0
                canvas?.drawCircle((i + 1) * 2f, (j + 1) * 2f, 2f, paint)
            }
        }


    }
}