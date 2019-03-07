package com.wy.younger.DesignPattern.a2策略模式

/**
 *@package:com.wy.younger.DesignPattern.策略模式
 *@data on:2019/2/28 13:38
 *author:YOUNG
 *desc:TODO
 *
 *
 *
 * 策略模式定义了算法家族，分别封装起来，让他们之间可以相互替代，次模式让算法的变化不会影响到使用算法的客户
 *
 *
 * 即同个功能的不同实现
 */

fun main(args: Array<String>) {

    val strategyContext = StrategyContext(ConcertStrategyA())
    strategyContext.contextInterface()
}