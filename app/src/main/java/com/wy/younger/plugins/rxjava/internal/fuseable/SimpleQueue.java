package com.wy.younger.plugins.rxjava.internal.fuseable;

import com.wy.younger.plugins.rxjava.annotations.NonNull;
import com.wy.younger.plugins.rxjava.annotations.Nullable;

/**
 * @package:com.wy.younger.plugins.rxjava.internal.fuseable
 * @data on:2019/1/22 14:02
 * author:YOUNG
 * desc:TODO
 */
public interface SimpleQueue<T> {

    boolean offer(@NonNull T value);

    boolean offer(@NonNull T v1, @NonNull T v2);

    @Nullable
    T poll() throws Exception;

    boolean isEmpty();

    void clear();

}
