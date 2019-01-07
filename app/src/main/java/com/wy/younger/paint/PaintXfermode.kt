package com.wy.younger.paint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wy.younger.R


/**
 * Paint Xfermode
 * paint是设置颜色的第三重境界
 * 处理源图像和 View 已有内容的关系
 *
 *
 * Xfermode 指的是你要绘制的内容和 Canvas 的目标位置的内容应该怎样结合计算出最终的颜色。
 * SRC：其实就是要你以绘制的内容作为源图像
 * DST：以 View 中已有的内容作为目标图像
 * PorterDuff.Mode 作为绘制内容的颜色处理方案
 *
 * 创建 Xfermode 的时候其实是创建的它的子类 PorterDuffXfermode
 * 历史原因造成了其只有一个子类
 *
 *
 *
 */
class PaintXfermode : View {
    var paint: Paint? = null
    var bitmap: Bitmap? = null

    var xfermode: PorterDuffXfermode? = null


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

        xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

        bitmap = BitmapFactory.decodeResource(resources, R.drawable.pic1)

        invalidate()
    }


    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)

        val saved: Int? = canvas?.saveLayer(null, null, Canvas.ALL_SAVE_FLAG)

        paint?.color = Color.RED
        canvas?.drawCircle(300f, 900f, 400f, paint)//DST


        bitmap = Bitmap.createScaledBitmap(bitmap, 600, 600, true)//SRC
        canvas?.drawBitmap(bitmap, 0f, 0f, paint)



        paint?.xfermode = xfermode
        canvas?.drawBitmap(bitmap, 0f, 600f, paint)
        paint?.xfermode = null

        canvas?.restoreToCount(saved!!)

    }
}