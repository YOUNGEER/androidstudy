package com.wy.younger.paint

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Paint作为画笔，它的各种api决定着画出来的图的各种样式
 */
class PaintView : View {
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
        paint = Paint()

        //下面七个是比较常用的方法
        paint?.color = Color.RED//1.设置颜色
        paint?.setARGB(0, 255, 255, 255)//2.设置颜色
        paint?.alpha = 0//3.设置透明度
        paint?.strokeWidth = 2f//4.设置画笔的宽度
        paint?.isAntiAlias = true//5.是否抗锯齿
        paint?.isDither = true//5.是否防抖动
        paint?.style = Paint.Style.FILL//7.绘图样式，fill表示填充，stroke表示只绘制边框
        paint?.style = Paint.Style.STROKE
        paint?.style = Paint.Style.FILL_AND_STROKE


        //8.设置着色器，一旦设置该属性，上面两个设置颜色的方法会不起作用了
        //Shader一般使用其子类
        //LinearGradient RadialGradient SweepGradient BitmapShader ComposeShader  如类：PaintShaderView.kt所示
//        paint?.shader=LinearGradient()


        //9.paint?.colorFilter 颜色过滤器


    }
}