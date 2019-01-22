package com.wy.younger.plugins.rxjava;

import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * @package:com.wy.younger.第三方框架.rxjava
 * @data on:2019/1/21 9:44
 * author:YOUNG
 * desc:TODO
 */
public class RxjavaDemo {


    /**
     * 分析源码之前，我们先定义一下名词，RxJava 是基于观察者模式的，
     * 这里将被观察者叫做主题（Source），观察者叫做观察者（Observer）。
     */


    private void method2() {

        /**
         * just操作符返回了一个Observable的子类ObservableJust对象
         */
        Observable observable1 = Observable.just(1);

        /**
         * 返回Observable的子类ObservableMap对象
         */
        Observable observable2 = observable1.map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return Math.abs(integer);
            }
        });

        /**
         * 1.调用了observable2的subscribe方法，
         * 2.实质是调用了observable2的 subscribeActual(observer);方法
         * 3.调用了ObservableMap的subscribeActual方法
         * 4.实质是调用了source也就是observable1的subscribeActual方法
         *
         * 5.再一步一步执行回调onNext方法
         */
        Disposable disposable = observable2.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {

            }
        });


        Observable.just(1).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return Math.abs(integer);
            }
        }).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return true;
            }
        }).observeOn(Schedulers.io())
                .subscribeOn(Schedulers.computation())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.i("gdsfafffffffffff", String.valueOf(integer));
                    }
                });
    }


    /**
     * 重点分析下线程是如何进行切换的
     */
    private void demo1() {
        Disposable d = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                emitter.onNext("1");
            }
        })
                .observeOn(Schedulers.io())

                .subscribeOn(Schedulers.computation())

                .map(new Function<String, String>() {

                    @Override
                    public String apply(String s) {
                        return s;
                    }
                })

                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {

                    }
                });

        d.dispose();

    }
}
