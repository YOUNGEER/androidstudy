package com.wy.younger.plugins.rxjava;


import com.wy.younger.plugins.rxjava.annotations.NonNull;
import com.wy.younger.plugins.rxjava.disposables.Disposable;

/**
 * @package:com.wy.younger.plugins.rxjava
 * @data on:2019/1/22 14:08
 * author:YOUNG
 * desc:TODO
 */
public interface SingleObserver<T> {


    void onSubscribe(@NonNull Disposable d);


    void onSuccess(@NonNull T t);


    void onError(@NonNull Throwable e);

}
