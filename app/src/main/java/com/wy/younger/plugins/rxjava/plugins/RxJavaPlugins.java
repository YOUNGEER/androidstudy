package com.wy.younger.plugins.rxjava.plugins;

import com.wy.younger.plugins.rxjava.annotations.Nullable;
import com.wy.younger.plugins.rxjava.plugins.functions.Consumer;

public final class RxJavaPlugins {

    @Nullable
    static volatile Consumer<? super Throwable> errorHandler;



}
