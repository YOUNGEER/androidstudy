package com.wy.younger.plugins.rxjava.annotations;

import io.reactivex.annotations.BackpressureKind;

import java.lang.annotation.*;

/**
 * @package:com.wy.younger.plugins.rxjava.annotations
 * @data on:2019/1/22 9:10
 * author:YOUNG
 * desc:TODO
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface BackpressureSupport {

    BackpressureKind value();
}
