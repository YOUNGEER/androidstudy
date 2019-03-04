package com.wy.younger.DesignPattern.策略模式;

/**
 * @package:com.wy.younger.DesignPattern.策略模式
 * @data on:2019/2/28 13:54
 * author:YOUNG
 * desc:TODO
 */
public class StrategyContext {
    public Strategy strategy;

    public StrategyContext(Strategy strategy) {
        this.strategy = strategy;
    }


    public void contextInterface() {
        strategy.AlgorithmInterface();
    }
}
