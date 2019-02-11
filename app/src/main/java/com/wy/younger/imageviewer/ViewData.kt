package com.wy.younger.imageviewer


class ViewData {
    // 目标 view 的 x 轴坐标
    var targetX: Float = 0f
    // 目标 view 的 y 轴坐标
    var targetY: Float = 0f
    // 目标 view 的宽度
    var targetWidth: Float = 0f
    // 目标 view 的高度
    var targetHeight: Float = 0f
    // 图片的原始宽度
    var imageWidth: Float = 0f
    // 图片的原始高度
    var imageHeight: Float = 0f

    constructor() {

    }

    constructor(targetX: Float, targetY: Float, targetWidth: Float, targetHeight: Float) {
        this.targetX = targetX
        this.targetY = targetY
        this.targetWidth = targetWidth
        this.targetHeight = targetHeight
    }
}
