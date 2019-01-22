package com.wy.younger.plugins.rxjava.internal.operators.observable;

import com.wy.younger.plugins.rxjava.Observable;
import com.wy.younger.plugins.rxjava.ObservableSource;
import com.wy.younger.plugins.rxjava.internal.functions.HasUpstreamObservableSource;

/**
 * @package:com.wy.younger.plugins.rxjava.internal.operators.observable
 * @data on:2019/1/22 11:46
 * author:YOUNG
 * desc:TODO
 */
public abstract class AbstractObservableWithUpstream<T, U> extends Observable<U> implements HasUpstreamObservableSource<T> {

    protected final ObservableSource<T> source;

    AbstractObservableWithUpstream(ObservableSource<T> source) {
        this.source = source;
    }

    @Override

    public final ObservableSource<T> source() {
        return source;
    }
}
