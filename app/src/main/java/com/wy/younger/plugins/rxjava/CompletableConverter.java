package com.wy.younger.plugins.rxjava;


import com.wy.younger.plugins.rxjava.annotations.NonNull;

/**
 * @package:com.wy.younger.plugins.rxjava
 * @data on:2019/1/22 9:15
 * author:YOUNG
 * desc:TODO
 */
public interface CompletableConverter<R> {
    /**
     * Applies a function to the upstream Completable and returns a converted value of type {@code R}.
     *
     * @param upstream the upstream Completable instance
     * @return the converted value
     */
    @NonNull
    R apply(@NonNull Completable upstream);
}
