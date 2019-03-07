package com.wy.younger.DesignPattern.a1简单工厂模式;

/**
 * @package:com.wy.younger.DesignPattern.简单工厂模式
 * @data on:2019/2/28 9:51
 * author:YOUNG
 * desc:TODO
 */
public class OperatorSub extends Operator {
    @Override
    public Double result() {

        return mA - mB;
    }
}
