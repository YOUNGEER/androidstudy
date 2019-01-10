package com.wy.younger.canvas

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wy.younger.R
import com.wy.younger.ScreenHeight
import com.wy.younger.ScreenWidht

/**
 *@package:com.wy.younger.canvas
 *@data on:2019/1/4 14:16
 *author:YOUNG
 *desc:TODO
 */

/**
 * 主要是学习下canvas camera可以实现三维的变化
 *
 */
class CanvasCamera : View {


    var camera: Camera? = null
    val centerX: Float = context.ScreenWidht() / 2.toFloat()
    val centerY: Float = context.ScreenHeight() / 2.toFloat()

    var degreeY: Float = 0f
    var rotate: Float = 0f
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
        camera = Camera()

        val displayMetrics = resources.displayMetrics
        val newZ = -displayMetrics.density * 6
        camera?.setLocation(0f, 0f, newZ)//使用 setLocation() 方法来把相机往后移动

        paint = Paint(Paint.ANTI_ALIAS_FLAG)

        bitmap = BitmapFactory.decodeResource(resources, R.drawable.pic1)
        bitmap = Bitmap.createScaledBitmap(bitmap!!, 600, 600, true)

        val valueAnimator3 = ObjectAnimator.ofFloat(-45f, 0f)
        valueAnimator3.addUpdateListener {
            degreeY = it.animatedValue as Float
            invalidate()
        }
        valueAnimator3.duration = 2000


        val valueAnimator2 = ObjectAnimator.ofFloat(0f, -360f)
        valueAnimator2.addUpdateListener {
            rotate = it.animatedValue as Float
            invalidate()
        }
        valueAnimator2.duration = 1000
        valueAnimator2?.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                valueAnimator3.start()
            }
        })


        val valueAnimator = ObjectAnimator.ofFloat(0f, -45f)
        valueAnimator.addUpdateListener {
            degreeY = it.animatedValue as Float
            invalidate()
        }
        valueAnimator.duration = 2000
        valueAnimator.start()

        valueAnimator?.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                valueAnimator2.start()
            }
        })


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        /**
         * 整体实现思路：开始分成左右两部分，当右边出现折叠效果后，再旋转canvas，使得折叠部分效果沿着canvas的旋转而旋转，最后再回复
         */
        val x = centerX - bitmap!!.width / 2
        val y = centerY - bitmap!!.height / 2

        //1. canvas.save  canvas.restore   camera.save    camera.restore保存状态，图层合成
        canvas?.save()
        camera?.save()

        canvas?.translate(centerX, centerY)//2.将canvas的左上角的坐标移动到屏幕中心
        //3.camera沿着y轴旋转，此时canvas会有折叠效果
        //camera的rotate是按照canvas的正常坐标系来的，如果坐标系进行了旋转，camera此时的旋转会和奇怪
        camera?.rotateY(degreeY)

        //10.degreeY动画完了后，执行canvas旋转动画，此时折叠的效果会随着canvas的旋转而旋转
        // 注意，千万不要把旋转放在了坐标系移动的前面去了
        canvas?.rotate(rotate)
        camera?.applyToCanvas(canvas)//4.将camera的效果应用到canvas上

        camera?.restore()
        //5.由于camera?.rotateY会旋转整个canvas，所以为了实现开始只有右半部分有折叠效果，所以此时可以clip右边的区域
        canvas?.clipRect(RectF(0f, -centerY, centerX, centerY))
        canvas?.rotate(-rotate)

        //6.再把canvas的坐标移到左上角，方便下次移动，复用代码
        canvas?.translate(-centerX, -centerY)
        //7.绘制图片
        canvas?.drawBitmap(bitmap!!, x, y, paint)
        canvas?.restore()


        canvas?.save()
        //8.移动坐标原点
        canvas?.translate(centerX, centerY)

        //11.degreeY动画完了后，执行canvas旋转动画，此时折叠的效果会随着canvas的旋转而旋转
        // 注意，千万不要把旋转放在了坐标系移动的前面去了
        canvas?.rotate(rotate)
        //9.剪切左半部分
        canvas?.clipRect(RectF(-centerX, -centerY, 0f, centerY))
        canvas?.rotate(-rotate)

        canvas?.translate(-centerX, -centerY)

        canvas?.drawBitmap(bitmap!!, x, y, paint)
        canvas?.restore()

    }


}