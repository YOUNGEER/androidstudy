package com.wy.younger.plugins.rxjava;

import com.wy.younger.plugins.rxjava.annotations.CheckReturnValue;
import com.wy.younger.plugins.rxjava.annotations.NonNull;
import com.wy.younger.plugins.rxjava.annotations.SchedulerSupport;
import com.wy.younger.plugins.rxjava.internal.functions.ObjectHelper;
import com.wy.younger.plugins.rxjava.internal.operators.observable.ObservableAmb;
import com.wy.younger.plugins.rxjava.plugins.RxJavaPlugins;

/**
 * @package:com.wy.younger.plugins.rxjava
 * @data on:2019/1/21 18:07
 * author:YOUNG
 * desc:TODO
 */
public abstract class Observable<T> implements ObservableSource<T> {

    @CheckReturnValue
    @NonNull
    @SchedulerSupport(SchedulerSupport.NONE)
    public static <T> Observable<T> amb(Iterable<? extends ObservableSource<? extends T>> sources) {
        ObjectHelper.requireNonNull(sources, "sources is null");
        return RxJavaPlugins.onAssembly(new ObservableAmb<T>(null, sources));
    }


    protected abstract void subscribeActual(Observer<? super T> observer);


    @Override
    public void subscribe(Observer<? super T> observer) {

    }
}
