package com.wy.younger.plugins.rxjava.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * @package:com.wy.younger.第三方框架.rxjava.annotations
 * @data on:2019/1/21 18:02
 * author:YOUNG
 * desc:TODO
 */
@Documented
@Target(value = {FIELD, METHOD, PARAMETER, LOCAL_VARIABLE})
@Retention(value = CLASS)
public @interface NonNull {
}
