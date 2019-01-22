package com.wy.younger.plugins.rxjava.internal.operators.observable;

import com.wy.younger.plugins.rxjava.ObservableSource;
import com.wy.younger.plugins.rxjava.Observer;
import com.wy.younger.plugins.rxjava.annotations.Nullable;
import com.wy.younger.plugins.rxjava.internal.functions.ObjectHelper;
import com.wy.younger.plugins.rxjava.plugins.functions.Function;

/**
 * @package:com.wy.younger.plugins.rxjava.internal.operators.observable
 * @data on:2019/1/22 13:25
 * author:YOUNG
 * desc:TODO
 */
public final class ObservableMap<T, U> extends AbstractObservableWithUpstream<T, U> {

    final Function<? super T, ? extends U> function;

    ObservableMap(ObservableSource<T> source, Function<? super T, ? extends U> function) {
        super(source);
        this.function = function;
    }


    @Override
    protected void subscribeActual(Observer<? super U> observer) {
        source.subscribe(new MapObserver<T, U>(observer, function));
    }


    static final class MapObserver<T, U> extends BasicFuseableObserver<T, U> {
        final Function<? super T, ? extends U> mapper;

        MapObserver(Observer<? super U> actual, Function<? super T, ? extends U> mapper) {
            super(actual);
            this.mapper = mapper;
        }

        @Override
        public void onNext(T t) {
            if (done) {
                return;
            }

            if (sourceMode != NONE) {
                downstream.onNext(null);
                return;
            }

            U v;

            try {
                v = ObjectHelper.requireNonNull(mapper.apply(t), "The mapper function returned a null value.");
            } catch (Throwable ex) {
                fail(ex);
                return;
            }
            downstream.onNext(v);
        }

        @Override
        public int requestFusion(int mode) {
            return transitiveBoundaryFusion(mode);
        }

        @Nullable
        @Override
        public U poll() throws Exception {
            T t = qd.poll();
            return t != null ? ObjectHelper.<U>requireNonNull(mapper.apply(t), "The mapper function returned a null value.") : null;
        }
    }
}
