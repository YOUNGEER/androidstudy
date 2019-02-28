package com.wy.younger.DesignPattern.简单工厂模式;

/**
 * @package:com.wy.younger.DesignPattern.简单工厂模式
 * @data on:2019/2/28 9:50
 * author:YOUNG
 * desc:TODO
 */
public class OperationFactory {

    public static Operator create(String operator) {
        Operator op = null;
        switch (operator) {

            case "+": {
                op = new OperatorAdd();
                break;
            }

            case "-": {
                op = new OperatorSub();
                break;
            }

            case "*": {
                op = new OperatorMul();
                break;
            }

            case "/":
                op = new OperatorDiv();
                break;
        }

        return op;
    }
}
