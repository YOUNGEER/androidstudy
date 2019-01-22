package com.wy.younger.plugins.rxjava;


import com.wy.younger.plugins.rxjava.annotations.NonNull;
import com.wy.younger.plugins.rxjava.disposables.Disposable;

/**
 * @package:com.wy.younger.plugins.rxjava
 * @data on:2019/1/22 11:37
 * author:YOUNG
 * desc:TODO
 */
public interface MaybeObserver<T> {

    /**
     * Provides the MaybeObserver with the means of cancelling (disposing) the
     * connection (channel) with the Maybe in both
     * synchronous (from within {@code onSubscribe(Disposable)} itself) and asynchronous manner.
     *
     * @param d the Disposable instance whose {@link Disposable#dispose()} can
     *          be called anytime to cancel the connection
     */
    void onSubscribe(@NonNull Disposable d);

    /**
     * Notifies the MaybeObserver with one item and that the {@link Maybe} has finished sending
     * push-based notifications.
     * <p>
     * The {@link Maybe} will not call this method if it calls {@link #onError}.
     *
     * @param t the item emitted by the Maybe
     */
    void onSuccess(@NonNull T t);

    /**
     * Notifies the MaybeObserver that the {@link Maybe} has experienced an error condition.
     * <p>
     * If the {@link Maybe} calls this method, it will not thereafter call {@link #onSuccess}.
     *
     * @param e the exception encountered by the Maybe
     */
    void onError(@NonNull Throwable e);

    /**
     * Called once the deferred computation completes normally.
     */
    void onComplete();
}
