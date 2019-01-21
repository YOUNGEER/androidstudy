package com.wy.younger.plugins.rxjava;

import com.wy.younger.plugins.rxjava.annotations.NonNull;
import com.wy.younger.plugins.rxjava.internal.functions.ObjectHelper;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.internal.operators.observable.ObservableAmb;
import io.reactivex.plugins.RxJavaPlugins;

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

}
