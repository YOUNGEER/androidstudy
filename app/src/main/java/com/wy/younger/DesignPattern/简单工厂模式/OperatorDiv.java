package com.wy.younger.DesignPattern.简单工厂模式;

/**
 * @package:com.wy.younger.DesignPattern.简单工厂模式
 * @data on:2019/2/28 9:51
 * author:YOUNG
 * desc:TODO
 */
public class OperatorDiv extends Operator {
    @Override
    public Double result() throws Exception {
        if (mB == 0) {
            throw new Exception("b cannot 0");
        }
        return mA / mB;
    }
}
