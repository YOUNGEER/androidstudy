package com.wy.younger.DesignPattern.简单工厂模式

/**
 *@package:com.wy.younger.DesignPattern.简单工厂模式
 *@data on:2019/2/28 10:11
 *author:YOUNG
 *desc:TODO
 *
 *
 *
 *
 *
 * 定义不同的实现的对象,即同个对象的不同功能
 *
 *
 *
 */


fun main(args: Array<String>) {
    val op = OperationFactory.create("+")

    op.mA = 5.0
    op.mB = 9.0
    println(op.result())

}