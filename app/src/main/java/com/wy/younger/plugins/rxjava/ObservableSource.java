package com.wy.younger.plugins.rxjava;


import com.wy.younger.plugins.rxjava.annotations.NonNull;

/**
 * @data on:2019/1/21 17:59
 * author:YOUNG
 * desc:TODO
 */
public interface ObservableSource<T> {

    void subscribe(@NonNull Observer<? super T> observer);
}
