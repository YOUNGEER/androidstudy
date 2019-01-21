package com.wy.younger.plugins.rxjava.plugins.functions;

import com.wy.younger.plugins.rxjava.annotations.NonNull;

public interface Function<T, R> {
    R apply(@NonNull T t) throws Exception;
}
