package com.wy.younger.DesignPattern.observer;

public interface Observor {

    void notified();


    void register(Observer observer);

    void unregister(Observer observer);
}
