package com.wy.younger.plugins.rxjava;

import com.wy.younger.plugins.rxjava.annotations.NonNull;
import com.wy.younger.plugins.rxjava.disposables.Disposable;

/**
 * @data on:2019/1/21 18:00
 * author:YOUNG
 * desc:TODO
 */
public interface Observer<T> {

    void onSubscribe(@NonNull Disposable d);

    void onNext(@NonNull T t);

    void onError(Throwable e);

    void onComplete();
}
