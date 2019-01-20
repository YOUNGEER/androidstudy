package com.wy.younger.DesignPattern.observer;

import java.util.ArrayList;
import java.util.List;

public class ConcertObservor implements Observor {

    private List<Observer> observers = new ArrayList<>();

    /**
     * 这时候如果是同步，容易造成线程阻塞，可以利用线程池实现
     */
    @Override
    public void notified() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updata();
        }
    }

    @Override
    public void register(Observer observer) {
        if (!observers.contains(observer))
            observers.add(observer);

    }

    @Override
    public void unregister(Observer observer) {
        if (observers.contains(observer))
            observers.remove(observer);
    }
}
