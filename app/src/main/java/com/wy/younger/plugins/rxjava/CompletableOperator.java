package com.wy.younger.plugins.rxjava;


import com.wy.younger.plugins.rxjava.annotations.NonNull;

/**
 * @package:com.wy.younger.plugins.rxjava
 * @data on:2019/1/22 9:12
 * author:YOUNG
 * desc:TODO
 */
public interface CompletableOperator {
    @NonNull
    io.reactivex.CompletableObserver apply(@NonNull CompletableObserver observer) throws Exception;
}
