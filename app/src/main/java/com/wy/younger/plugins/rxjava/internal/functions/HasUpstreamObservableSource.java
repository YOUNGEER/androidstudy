package com.wy.younger.plugins.rxjava.internal.functions;


import com.wy.younger.plugins.rxjava.ObservableSource;

/**
 * @package:com.wy.younger.plugins.rxjava.internal.functions
 * @data on:2019/1/22 11:30
 * author:YOUNG
 * desc:TODO
 */
public interface HasUpstreamObservableSource<T> {

    ObservableSource<T> source();

}
