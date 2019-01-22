package com.wy.younger.plugins.rxjava.internal.functions;


import com.wy.younger.plugins.rxjava.MaybeSource;

/**
 * @package:com.wy.younger.plugins.rxjava.internal.functions
 * @data on:2019/1/22 11:35
 * author:YOUNG
 * desc:TODO
 */
public interface HasUpstreamMaybeSource<T> {

    MaybeSource<T> source();
}
