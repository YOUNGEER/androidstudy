package com.wy.younger.path

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class PathDirection : View {
    var path1: Path? = null
    var path2: Path? = null
    var paint: Paint? = null

    var path3: Path? = null
    var path4: Path? = null
    var path5: Path? = null
    var path6: Path? = null
    var path7: Path? = null
    var path8: Path? = null


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
        paint?.textSize = 30f

        path1 = Path()
        path2 = Path()
        path3 = Path()
        path4 = Path()
        path5 = Path()
        path6 = Path()
        path7 = Path()
        path8 = Path()
        path4 = Path()

        /**
         * direction：CW,顺时针 Clockwise     CCW，逆时针  Counterclockwise
         * path的路径显示原则是根据：Direction+fillType决定的，Direction默认是CW
         * fillType有四种类型：
         * 非零环绕原则(WINDING)----默认
         * 反零环绕原则(INVERSE_WINDING)
         * 奇偶环绕原则(EVEN_ODD)
         * 反奇偶环绕原则(INVERSE_EVEN_ODD)
         *
         *
         * WINDING：从任一点引出一条射线，遇到顺时针+1，逆时针-1，如果和为0，不显示内容，非0显示内容
         * INVERSE_WINDING：与WINDING相反
         *
         * EVEN_ODD：从任一点引出一条射线，和路径想交点的个数和，奇数显示，偶数不显示
         * INVERSE_EVEN_ODD：与EVEN_ODD相反
         *
         */
        path1?.addCircle(100f, 100f, 100f, Path.Direction.CW)//顺时针画矩形
        path1?.addCircle(200f, 100f, 100f, Path.Direction.CCW)//逆时针画矩形

        path2?.addCircle(100f, 100f, 100f, Path.Direction.CW)//顺时针画矩形
        path2?.addCircle(200f, 100f, 100f, Path.Direction.CW)//顺时针画矩形


        /**
         * 对于两个path的重叠效果，用到op方法
         *
         * left.op(right, Path.Op.DIFFERENCE);//差集
         * left.op(right, Path.Op.REVERSE_DIFFERENCE);//反差集
         * left.op(right, Path.Op.INTERSECT);//交集
         * left.op(right, Path.Op.UNION);//并集
         * left.op(right, Path.Op.XOR);//异或集
         *
         *
         * 注意：此时right的path不需要进行绘制的
         *
         */
        path3?.addCircle(100f, 100f, 100f, Path.Direction.CW)
        path5?.addCircle(100f, 100f, 100f, Path.Direction.CW)
        path6?.addCircle(100f, 100f, 100f, Path.Direction.CW)
        path7?.addCircle(100f, 100f, 100f, Path.Direction.CW)
        path8?.addCircle(100f, 100f, 100f, Path.Direction.CW)

        path4?.addCircle(200f, 100f, 100f, Path.Direction.CW)


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

//        canvas?.save()
        canvas?.drawPath(path1, paint)
        canvas?.drawText("圆1顺时针，圆2逆时针，filltype默认 WINDING", 400f, 150f, paint)

        canvas?.translate(0f, 300f)
        path1?.fillType = Path.FillType.EVEN_ODD
        canvas?.drawPath(path1, paint)
        canvas?.drawText("圆1顺时针，圆2逆时针，filltype默认 EVEN_ODD", 400f, 150f, paint)

        canvas?.translate(0f, 300f)
        path2?.fillType = Path.FillType.WINDING
        canvas?.drawPath(path2, paint)
        canvas?.drawText("圆1顺时针，圆2顺时针，filltype默认 WINDING", 400f, 150f, paint)

        canvas?.translate(0f, 300f)
        path2?.fillType = Path.FillType.EVEN_ODD
        canvas?.drawPath(path2, paint)
        canvas?.drawText("圆1顺时针，圆2顺时针，filltype默认 EVEN_ODD", 400f, 150f, paint)

        canvas?.translate(0f, 300f)
        path3?.op(path4, Path.Op.DIFFERENCE)
        canvas?.drawPath(path3, paint)
        canvas?.drawText("圆1顺时针，圆2顺时针，op默认 DIFFERENCE", 400f, 150f, paint)

        canvas?.translate(0f, 300f)
        path5?.op(path4, Path.Op.REVERSE_DIFFERENCE)
        canvas?.drawPath(path5, paint)
        canvas?.drawText("圆1顺时针，圆2顺时针，op默认 REVERSE_DIFFERENCE", 400f, 150f, paint)


        canvas?.translate(0f, 300f)
        path6?.op(path4, Path.Op.INTERSECT)
        canvas?.drawPath(path6, paint)
        canvas?.drawText("圆1顺时针，圆2顺时针，op默认 INTERSECT", 400f, 150f, paint)

        canvas?.translate(0f, 300f)
        path7?.op(path4, Path.Op.UNION)
        canvas?.drawPath(path7, paint)
        canvas?.drawText("圆1顺时针，圆2顺时针，op默认 UNION", 400f, 150f, paint)

        canvas?.translate(0f, 300f)
        path8?.op(path4, Path.Op.XOR)
        canvas?.drawPath(path8, paint)
        canvas?.drawText("圆1顺时针，圆2顺时针，op默认 XOR", 400f, 150f, paint)


    }

}