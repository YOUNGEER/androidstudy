package com.wy.younger.数据结构;

/**
 * @package:com.wy.younger.数据结构
 * @data on:2019/1/14 9:56
 * author:YOUNG
 * desc:TODO
 */
public class SingleNodeDemo implements Position {
    private Object object;
    private SingleNodeDemo next;

    public SingleNodeDemo() {
        this(null, null);
    }

    public SingleNodeDemo(Object object, SingleNodeDemo next) {
        this.object = object;
        this.next = next;
    }

    public SingleNodeDemo getNext() {
        return next;
    }

    public void setNext(SingleNodeDemo next) {
        this.next = next;
    }

    @Override
    public Object getElem() {
        return object;
    }

    @Override
    public Object setElem(Object e) {
        Object old = this.object;
        this.object = e;
        return this.object;
    }
}
