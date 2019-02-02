package com.wy.younger.imageviewer


import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.ImageView

object Utils {

    /**
     * 获取屏幕尺寸
     *
     * @param context
     * @return
     */
    fun getScreenSize(context: Context): Point {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return Point(outMetrics.widthPixels, outMetrics.heightPixels)
    }

    /**
     * 获取状态栏的高度
     *
     * @return 状态栏的高度
     */
    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    /**
     * dp 转 px
     *
     * @param dpVal dp 值
     * @return px 值
     */
    fun dp2px(context: Context, dpVal: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpVal * scale + 0.5f).toInt()
    }

    /**
     * 回收 ImageView 占用的图像内存
     *
     * @param view
     */
    fun recycleImage(view: ImageView?) {
        if (view == null) return
        val drawable = view.drawable
        if (drawable != null && drawable is BitmapDrawable) {
            var bitmap: Bitmap? = drawable.bitmap
            if (bitmap != null && !bitmap.isRecycled) {
                /**
                 * 一些具有缓存机制的图片加载框架在加载图片时，会缓存 Bitmap；
                 * 当 Bitmap 被 recycle 后，框架不知道 Bitmap 已被回收；
                 * 加载相同的 url 时，可能会返回被 recycle 的图片，
                 * 出现异常 BitmapDrawable: Canvas: trying to use a recycled bitmap，
                 * 故此处暂且先注释掉 bitmap.recycle() 方法
                 */
                //                bitmap.recycle();
                bitmap = null
            }
        }
        // 调用 setImageDrawable(null)，对应图片的回收会有 GC 来完成
        view.setImageDrawable(null)
    }
}
