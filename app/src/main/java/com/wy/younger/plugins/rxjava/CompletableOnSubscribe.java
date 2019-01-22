package com.wy.younger.plugins.rxjava;


import com.wy.younger.plugins.rxjava.annotations.NonNull;

/**
 * @package:com.wy.younger.plugins.rxjava
 * @data on:2019/1/22 9:14
 * author:YOUNG
 * desc:TODO
 */
public interface CompletableOnSubscribe {
    /**
     * Called for each CompletableObserver that subscribes.
     *
     * @param emitter the safe emitter instance, never null
     * @throws Exception on error
     */
    void subscribe(@NonNull CompletableEmitter emitter) throws Exception;
}
