package com.wy.younger.views

import android.content.Context
import android.content.res.Configuration
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.*
import android.widget.Scroller

/**
 * 该类主要介绍 自定义view的时候会用到的一些有用的第三方的类
 */
class ViewTools : View {

    /**1.
     *ViewConfiguration提供了一些自定义控件用到的标准常量，比如尺寸大小，滑动距离，敏感度等等。
    可以利用ViewConfiguration的静态方法获取一个实例
     */
    var viewConfiguration: ViewConfiguration? = null

    fun initViewConfiguration() {
        viewConfiguration = ViewConfiguration.get(context)

        //获取touchSlop。该值表示系统所能识别出的被认为是滑动的最小距离
        val touchSlop = viewConfiguration?.scaledTouchSlop

        //获取Fling速度的最小值和最大值
        val flingMin = viewConfiguration?.scaledMinimumFlingVelocity
        val flingMax = viewConfiguration?.scaledMaximumFlingVelocity

        //判断是否有物理按键
        val isPermanentMenuKey = viewConfiguration?.hasPermanentMenuKey()

        //双击间隔时间.在该时间内是双击，否则是单击
        val doubleTapTimeout = ViewConfiguration.getDoubleTapTimeout()
        //按住状态转变为长按状态需要的时间
        val longPressTimeout = ViewConfiguration.getLongPressTimeout()
        //重复按键的时间
        val keyRepeatTimeout = ViewConfiguration.getKeyRepeatTimeout()


    }

    /**
     *2.
     * Configuration用来描述设备的配置信息。
    比如用户的配置信息：locale和scaling等等
    比如设备的相关信息：输入模式，屏幕大小， 屏幕方向等等
    我们经常采用如下方式来获取需要的相关信息:
     */
    var configuration: Configuration? = null

    fun initConfiguration() {
        configuration = resources.configuration
        ////获取国家码
        val countryCode = configuration?.mcc
        //获取网络码
        val networkCode = configuration?.mnc
        //判断横竖屏
        if (configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {

        } else {

        }
    }

    /**
     * 3.
     * 手势
     */
    var gestureDetector: GestureDetector? = null

    fun initGestureDetector() {
        gestureDetector = GestureDetector(context, object : GestureDetector.OnGestureListener {
            //手指在屏幕上按下,且未移动和松开时调用该方法
            override fun onShowPress(e: MotionEvent?) {

            }

            //轻击屏幕时调用该方法
            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                return true
            }

            //触摸屏幕时均会调用该方法
            override fun onDown(e: MotionEvent?): Boolean {
                return true
            }

            //手指在屏幕上拖动时会调用该方法
            override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
                return true
            }

            //手指在屏幕上滚动时会调用该方法
            override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
                return true
            }

            //手指长按屏幕时均会调用该方法
            override fun onLongPress(e: MotionEvent?) {

            }

        })
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
        gestureDetector?.onTouchEvent(event)
    }


    /**
     * 4.
     * 用于跟踪触摸屏事件（比如，Flinging及其他Gestures手势事件等）的速率。
     *
     * 1.设置速度追踪
     * 2.返回追踪到的速度
     * 3.接触速度追踪
     */
    var velocityTracker: VelocityTracker? = null

    fun bindVelocityTracker(event: MotionEvent) {
        if (null == velocityTracker) {
            velocityTracker = VelocityTracker.obtain()
        }
        velocityTracker?.addMovement(event)
    }

    fun scrollVelocity(): Int {
        // 设置VelocityTracker单位.1000表示1秒时间内运动的像素
        velocityTracker?.computeCurrentVelocity(1000);
        // 获取在1秒内X方向所滑动像素值
        val xVelocity = velocityTracker?.xVelocity
        return Math.abs(xVelocity!!).toInt()
    }

    fun unbindVelocity() {
        if (null != velocityTracker) {
            velocityTracker?.recycle()
            velocityTracker = null
        }
    }

    /**
     * 5.
     * Scroller 控件的内容滑动
     * 滑动距离和view的坐标系方向相反
     * scrollBy
     * scrollTo
     */

    var scroller: Scroller? = null

    fun initScroller() {
        scroller = Scroller(context)

    }

    /**
     * 6.
     * ViewDragHelper
     * 拖拽滑动的辅助类
     * 在项目中很多场景需要用户手指拖动其内部的某个View，
     * 此时就需要在onInterceptTouchEvent()和onTouchEvent()
     * 这两个方法中写不少逻辑了，比如处理：拖拽移动，越界，多手指的按下，加速度检测等等。
     * ViewDragHelper可以极大的帮我们简化类似的处理，它提供了一系列用于
     * 处理用户拖拽子View的辅助方法和与其相关的状态记录。
     * 比较常见的：QQ侧滑菜单，Navigation Drawer的边缘滑动，都可以由它实现
     */

    var viewDragHelper: ViewDragHelper? = null

    fun initViewDragHelper() {
        viewDragHelper = ViewDragHelper.create(this.parent as ViewGroup, 1.0f, object : ViewDragHelper.Callback() {
            override fun tryCaptureView(p0: View, p1: Int): Boolean {
                return true
            }

            //处理水平方向的越界
            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                var fixedLeft = 0
                val parent = child.getParent() as View
                val leftBound = parent.paddingLeft
                val rightBound = parent.width - child.width - parent.paddingRight

                if (left < leftBound) {
                    fixedLeft = leftBound;
                } else if (left > rightBound) {
                    fixedLeft = rightBound;
                } else {
                    fixedLeft = left;
                }
                return fixedLeft;
            }

            //处理垂直方向的越界
            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                var fixedTop = 0
                val parent = child.getParent() as View
                val topBound = paddingTop
                val bottomBound = height - child.height - parent.paddingBottom
                if (top < topBound) {
                    fixedTop = topBound;
                } else if (top > bottomBound) {
                    fixedTop = bottomBound;
                } else {
                    fixedTop = top;
                }
                return fixedTop;
            }

            //监听拖动状态的改变
            override fun onViewDragStateChanged(state: Int) {
                super.onViewDragStateChanged(state)
                when (state) {
                    ViewDragHelper.STATE_DRAGGING -> {
                        System.out.println("STATE_DRAGGING");
                    }
                    ViewDragHelper.STATE_IDLE -> {
                        System.out.println("STATE_IDLE");
                    }
                    ViewDragHelper.STATE_SETTLING -> {
                        System.out.println("STATE_SETTLING");
                    }
                }
            }

            //捕获View
            override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
                super.onViewCaptured(capturedChild, activePointerId)
            }

            //释放View
            override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
                super.onViewReleased(releasedChild, xvel, yvel)
            }

        })
    }

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {

    }

    fun initView() {
        initConfiguration()
    }


}