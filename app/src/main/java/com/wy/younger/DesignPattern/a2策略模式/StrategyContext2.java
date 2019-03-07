package com.wy.younger.DesignPattern.a2策略模式;

/**
 * @package:com.wy.younger.DesignPattern.策略模式
 * @data on:2019/2/28 15:03
 * author:YOUNG
 * desc:TODO
 * <p>
 * 策略模式和简单工厂模式结合
 */
public class StrategyContext2 {

    public Strategy strategy;

    public StrategyContext2(String type) {
        switch (type) {
            case "正常收费":
                strategy = new ConcertStrategyA();
                break;

            case "打折":
                strategy = new ConcertStrategyB();
                break;

            case "返现":
                strategy = new ConcertStrategyC();
                break;
        }
    }

    public void run() {
        strategy.AlgorithmInterface();
    }

}
