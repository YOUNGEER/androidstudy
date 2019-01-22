package com.wy.younger.plugins.rxjava;

/**
 * @package:com.wy.younger.animator
 * @data on:2019/1/22 9:02
 * author:YOUNG
 * desc:TODO
 */
public enum BackpressureOverflowStrategy {
    /**
     * Signal a MissingBackpressureException and terminate the sequence.
     */
    ERROR,
    /**
     * Drop the oldest value from the buffer.
     */
    DROP_OLDEST,
    /**
     * Drop the latest value from the buffer.
     */
    DROP_LATEST
}
