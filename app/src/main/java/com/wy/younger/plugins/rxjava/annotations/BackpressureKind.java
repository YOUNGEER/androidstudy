package com.wy.younger.plugins.rxjava.annotations;

/**
 * @package:com.wy.younger.plugins.rxjava.annotations
 * @data on:2019/1/22 9:09
 * author:YOUNG
 * desc:TODO
 */
public enum BackpressureKind {
    /**
     * The backpressure-related requests pass through this operator without change.
     */
    PASS_THROUGH,
    /**
     * The operator fully supports backpressure and may coordinate downstream requests
     * with upstream requests through batching, arbitration or by other means.
     */
    FULL,
    /**
     * The operator performs special backpressure management; see the associated javadoc.
     */
    SPECIAL,
    /**
     * The operator requests Long.MAX_VALUE from upstream but respects the backpressure
     * of the downstream.
     */
    UNBOUNDED_IN,
    /**
     * The operator will emit a MissingBackpressureException if the downstream didn't request
     * enough or in time.
     */
    ERROR,
    /**
     * The operator ignores all kinds of backpressure and may overflow the downstream.
     */
    NONE
}
