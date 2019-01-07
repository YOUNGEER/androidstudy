package com.wy.younger.paint

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.wy.younger.ScreenWidht

/**
 * drawText
 * 这是canvas的方法，但是很多都是Paint来决定的
 *
 * drawText:x,y表示左下角，其中下的位置表示text的基线位置
 * drawTextRun：用的不多
 * drawTextOnPath：
 *  canvas?.drawTextOnPath("wy is learning paint text", path, 0f, 0f, paint)
 * 沿着path绘制 hOffset 和 vOffset。
 * 它们是文字相对于 Path 的水平偏移量和竖直偏移量，
 * 利用它们可以调整文字的位置。例如你设置 hOffset 为 5，
 * vOffset 为 10，文字就会右移 5 像素和下移 10 像素。
 *
 *
 * StaticLayout：StaticLayout 并不是一个 View 或者 ViewGroup ，
 * 而是 android.text.Layout 的子类，它是纯粹用来绘制文字的。
 * StaticLayout 支持换行，它既可以为文字设置宽度上限来让文字自动换行，
 * 也会在 \n 处主动换行。
 *
 *
 *
 */
class PaintText : View {

    var paint: Paint? = null
    var bitmap: Bitmap? = null

    var path: Path? = null
    var staticLayout: StaticLayout? = null


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
        paint?.color = Color.RED
        paint?.textSize = 50f

        paint?.style = Paint.Style.STROKE


        path = Path()

        path?.rLineTo(100f, 200f)
        path?.rLineTo(100f, -100f)
        path?.rLineTo(200f, 200f)


        val tetPaint = TextPaint()
        tetPaint.textSize = 50f
        tetPaint.color = Color.RED


        //width 是文字区域的宽度，文字到达这个宽度后就会自动换行；
        //align 是文字的对齐方向；
        //spacingmult 是行间距的倍数，通常情况下填 1 就好；
        //spacingadd 是行间距的额外增加值，通常情况下填 0 就好；
        //includeadd 是指是否在文字上下添加额外的空间，来避免某些过高的字符的绘制出现越界。
        staticLayout = StaticLayout(
            "wy is learning paint text i think view is very nice ,hehhah", tetPaint, 600,
            Layout.Alignment.ALIGN_NORMAL, 1f, 0f, true
        )




        invalidate()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun paintpi() {

        paint?.textSize//字的大小

        paint?.typeface//字体
        paint?.isFakeBoldText = true//是否使用伪粗体
        paint?.isStrikeThruText = true//是否加删除线
        paint?.isUnderlineText = true//是否加下划线。

        paint?.textSkewX = 0.5f//设置文字横向错切角度。其实就是文字倾斜度的啦
        paint?.textScaleX = 0.8f //文字缩放
        paint?.letterSpacing = 0.2f //api21支持    字符间距
        paint?.textAlign = Paint.Align.CENTER //文字对齐,此时的drawText的想，x，y坐标是参照对齐点的坐标

        paint?.isSubpixelText = true//是否开启次像素级的抗锯齿

        paint?.fontSpacing //获取推荐的行距,即推荐的两行文字的 baseline 的距离,可以在换行的时候给 y 坐标加上这个值来下移文字

        paint?.fontMetrics//FontMetrics 是个相对专业的工具类，它提供了几个文字排印方面的数值：ascent, descent, top, bottom,  leading。
        //另外，ascent 和 descent 这两个值还可以通过 Paint.ascent() 和 Paint.descent() 来快捷获取。

        //文字从上到下依次为：top   ascent   baseline    descent   bottom
        // leading是上个bottom 到下个top的距离

//        paint?.getTextBounds()//获取文字的范围

        paint?.measureText("")//measureText


//        paint?.breakText()  //这个方法也是用来测量文字宽度的。但和 measureText() 的区别是，
//        breakText() 是在给出宽度上限的前提下测量文字的宽度。如果文字的宽度超出了上限，那么在临近超限的位置截断文字。

    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint?.setShadowLayer(10f, 0f, 0f, Color.GREEN)//12.设置的是在绘制层下方的附加效果。
        canvas?.save()
        canvas?.drawText("paint study", 20f, 50f, paint)//左下角，其中下的位置表示text的基线位置


        canvas?.translate(0f, 100f)

        canvas?.drawPath(path, paint)
        canvas?.drawTextOnPath("wy is learning paint text", path, 10f, 5f, paint)

        /**
         * 以上drawtext的文字不会自动换行，
         * 自动换行需要staticLayout
         */

        canvas?.translate(0f, 400f)
        staticLayout?.draw(canvas)


        canvas?.translate(0f, 200f)
        paint?.isFakeBoldText = true
        canvas?.drawText("wy is learning paint text", 10f, 50f, paint)

        canvas?.translate(0f, 200f)
        paint?.isFakeBoldText = false
        paint?.isStrikeThruText = true
        canvas?.drawText("wy is learning paint text", 10f, 50f, paint)


        canvas?.translate(0f, 200f)
        paint?.isStrikeThruText = false
        paint?.isUnderlineText = true
        canvas?.drawText("wy is learning paint text", 10f, 50f, paint)

        canvas?.translate(0f, 200f)

        paint?.isUnderlineText = false
        paint?.textSkewX = 0.5f
        canvas?.drawText("wy is learning paint text", 10f, 50f, paint)

        canvas?.translate(0f, 200f)
        paint?.textSkewX = 0f
        paint?.textScaleX = 0.8f
        canvas?.drawText("wy is learning paint text", 10f, 50f, paint)


        canvas?.translate(0f, 200f)
        paint?.letterSpacing = 0.2f
        paint?.textScaleX = 1f
        canvas?.drawText("wy is learning paint text", 10f, 50f, paint)

        canvas?.translate(0f, 200f)
        paint?.letterSpacing = 0f
        paint?.textAlign = Paint.Align.CENTER
        canvas?.drawText("wy is learning paint text", context.ScreenWidht().toFloat() / 2, 50f, paint)
        canvas?.translate(0f, 100f)
        paint?.letterSpacing = 0f
        paint?.textAlign = Paint.Align.LEFT
        canvas?.drawText("wy is learning paint text", context.ScreenWidht().toFloat() / 2, 50f, paint)
        canvas?.translate(0f, 100f)
        paint?.letterSpacing = 0f
        paint?.textAlign = Paint.Align.RIGHT
        canvas?.drawText("wy is learning paint text", context.ScreenWidht().toFloat() / 2, 50f, paint)



        canvas?.translate(0f, 200f)
        val r = Rect()
        canvas?.drawText("wy is learning paint text", 0f, 20f, paint)

        paint?.getTextBounds("wy is learning paint text", 0, 20, r)//计算字符占用的范围，结果在r中
        canvas?.drawRect(r, paint)
        paint?.measureText("wy is learning paint text")//计算字符串的宽度
        paint?.getTextWidths("wy is learning paint text", floatArrayOf())//计算每个字符的宽度，结果在数组中


        canvas?.restore()


    }
}