package com.wy.younger.plugins.rxjava;

import com.wy.younger.plugins.rxjava.annotations.NonNull;

/**
 * @package:com.wy.younger.plugins.rxjava.annotations
 * @data on:2019/1/22 9:04
 * author:YOUNG
 * desc:TODO
 */
public interface CompletableSource {

    void subscribe(@NonNull CompletableObserver observer);

}
