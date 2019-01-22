package com.wy.younger.plugins.rxjava;


import com.wy.younger.plugins.rxjava.annotations.NonNull;

/**
 * @package:com.wy.younger.plugins.rxjava
 * @data on:2019/1/22 9:11
 * author:YOUNG
 * desc:TODO
 */
public interface CompletableTransformer {
    @NonNull
    CompletableSource apply(@NonNull Completable upstream);
}
