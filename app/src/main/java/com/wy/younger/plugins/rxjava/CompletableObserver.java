package com.wy.younger.plugins.rxjava;


import com.wy.younger.plugins.rxjava.annotations.NonNull;
import com.wy.younger.plugins.rxjava.disposables.Disposable;

/**
 * @package:com.wy.younger.plugins.rxjava.annotations
 * @data on:2019/1/22 9:05
 * author:YOUNG
 * desc:TODO
 */
public interface CompletableObserver {
    /**
     * Called once by the Completable to set a Disposable on this instance which
     * then can be used to cancel the subscription at any time.
     *
     * @param d the Disposable instance to call dispose on for cancellation, not null
     */
    void onSubscribe(@NonNull Disposable d);

    /**
     * Called once the deferred computation completes normally.
     */
    void onComplete();

    /**
     * Called once if the deferred computation 'throws' an exception.
     *
     * @param e the exception, not null.
     */
    void onError(@NonNull Throwable e);
}
