package com.wy.younger.数据结构;

import java.util.Stack;

public class StackDemo extends Stack<String> {

    @Override
    public String push(String item) {
        return super.push(item);

    }

    @Override
    public synchronized String pop() {
        return super.pop();
    }

    @Override
    public synchronized int size() {
        return super.size();
    }

    @Override
    public boolean empty() {
        return super.empty();
    }

    @Override
    public synchronized String peek() {
        return super.peek();
    }


}
