package com.wy.younger.imageviewer.photoview

import android.content.Context
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ViewConfiguration

/**
 *@package:com.wy.younger.imageviewer.photoview
 *@data on:2019/2/2 11:10
 *author:YOUNG
 *desc:TODO
 */

class CustomGestureDetector(var context: Context, var onGestureListener: OnGestureListener) {

    var viewConfiguration: ViewConfiguration? = null
    var mDetector: ScaleGestureDetector? = null

    var mTouchSlop: Int = 0
    var mMinimumVelocity: Int = 0


    init {
        viewConfiguration = ViewConfiguration.get(context)
        mTouchSlop = viewConfiguration!!.scaledTouchSlop
        mMinimumVelocity = viewConfiguration!!.scaledMinimumFlingVelocity

        val mScaleListener = object : ScaleGestureDetector.OnScaleGestureListener {
            override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {

                return true
            }

            override fun onScaleEnd(detector: ScaleGestureDetector?) {

            }

            override fun onScale(detector: ScaleGestureDetector?): Boolean {
                val scaleFactor = detector!!.scaleFactor
                if (java.lang.Float.isNaN(scaleFactor) || java.lang.Float.isInfinite(scaleFactor))
                    return false
                if (scaleFactor >= 0) {
                    onGestureListener.onScale(
                        scaleFactor,
                        detector.focusX, detector.focusY
                    )
                }
                return true
            }

        }
        mDetector = ScaleGestureDetector(context, mScaleListener)


    }


    fun getActiviX(ev: MotionEvent) {

    }

    fun getActiviY(ev: MotionEvent) {

    }

}