package com.wy.younger.plugins.rxjava;


import com.wy.younger.plugins.rxjava.annotations.NonNull;
import com.wy.younger.plugins.rxjava.annotations.Nullable;
import com.wy.younger.plugins.rxjava.disposables.Disposable;
import com.wy.younger.plugins.rxjava.plugins.functions.Cancellable;

/**
 * @package:com.wy.younger.plugins.rxjava
 * @data on:2019/1/22 9:13
 * author:YOUNG
 * desc:TODO
 */
public interface CompletableEmitter {

    /**
     * Signal the completion.
     */
    void onComplete();

    /**
     * Signal an exception.
     *
     * @param t the exception, not null
     */
    void onError(@NonNull Throwable t);

    /**
     * Sets a Disposable on this emitter; any previous {@link Disposable}
     * or {@link Cancellable} will be disposed/cancelled.
     *
     * @param d the disposable, null is allowed
     */
    void setDisposable(@Nullable Disposable d);

    /**
     * Sets a Cancellable on this emitter; any previous {@link Disposable}
     * or {@link Cancellable} will be disposed/cancelled.
     *
     * @param c the cancellable resource, null is allowed
     */
    void setCancellable(@Nullable Cancellable c);

    /**
     * Returns true if the downstream disposed the sequence or the
     * emitter was terminated via {@link #onError(Throwable)},
     * {@link #onComplete} or a successful {@link #tryOnError(Throwable)}.
     * <p>This method is thread-safe.
     *
     * @return true if the downstream disposed the sequence or the emitter was terminated
     */
    boolean isDisposed();

    /**
     * Attempts to emit the specified {@code Throwable} error if the downstream
     * hasn't cancelled the sequence or is otherwise terminated, returning false
     * if the emission is not allowed to happen due to lifecycle restrictions.
     * <p>
     * Unlike {@link #onError(Throwable)}, the {@code RxJavaPlugins.onError} is not called
     * if the error could not be delivered.
     * <p>History: 2.1.1 - experimental
     *
     * @param t the throwable error to signal if possible
     * @return true if successful, false if the downstream is not able to accept further
     * events
     * @since 2.2
     */
    boolean tryOnError(@NonNull Throwable t);
}
