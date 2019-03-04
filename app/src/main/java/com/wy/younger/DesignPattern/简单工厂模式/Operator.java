package com.wy.younger.DesignPattern.简单工厂模式;

/**
 * @package:com.wy.younger.DesignPattern.简单工厂模式
 * @data on:2019/2/28 9:48
 * author:YOUNG
 * desc:TODO
 */
public abstract class Operator {

    Double mA;
    Double mB;

    public abstract Double result() throws Exception;

}
