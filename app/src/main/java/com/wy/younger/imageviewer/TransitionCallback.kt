package com.wy.younger.imageviewer

/**
 * 进退场过渡效果的回调监听
 */
abstract class TransitionCallback {
    /**
     * 过渡效果开始执行
     */
    fun onTransitionStart() {

    }

    /**
     * 过渡效果执行中
     *
     * @param progress 执行进度,范围 0-1
     */
    fun onTransitionRunning(progress: Float) {

    }

    /**
     * 过渡效果执行完毕
     */
    fun onTransitionEnd() {

    }
}
