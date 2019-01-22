package com.wy.younger.plugins.rxjava;


import com.wy.younger.plugins.rxjava.annotations.NonNull;

/**
 * @package:com.wy.younger.plugins.rxjava
 * @data on:2019/1/22 11:36
 * author:YOUNG
 * desc:TODO
 */
public interface MaybeSource<T> {

    void subscribe(@NonNull MaybeObserver<? super T> observer);
}
