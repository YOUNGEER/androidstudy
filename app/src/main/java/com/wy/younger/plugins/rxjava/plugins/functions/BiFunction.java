package com.wy.younger.plugins.rxjava.plugins.functions;

import com.wy.younger.plugins.rxjava.annotations.NonNull;

public interface BiFunction<T1, T2, R> {

    @NonNull
    R apply(@NonNull T1 t1, @NonNull T2 t2) throws Exception;

}
