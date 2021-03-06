package com.wy.younger.plugins.rxjava.internal.fuseable;

/**
 * @package:com.wy.younger.plugins.rxjava.internal.fuseable
 * @data on:2019/1/22 14:04
 * author:YOUNG
 * desc:TODO
 */
public interface QueueFuseable<T> extends SimpleQueue<T> {
    /**
     * Returned by the {@link #requestFusion(int)} if the upstream doesn't support
     * the requested mode.
     */
    int NONE = 0;

    /**
     * Request a synchronous fusion mode and can be returned by {@link #requestFusion(int)}
     * for an accepted mode.
     * <p>
     * In synchronous fusion, all upstream values are either already available or is generated
     * when {@link #poll()} is called synchronously. When the {@link #poll()} returns null,
     * that is the indication if a terminated stream.
     * In this mode, the upstream won't call the onXXX methods and callers of
     * {@link #poll()} should be prepared to catch exceptions. Note that {@link #poll()} has
     * to be called sequentially (from within a serializing drain-loop).
     */
    int SYNC = 1;

    /**
     * Request an asynchronous fusion mode and can be returned by {@link #requestFusion(int)}
     * for an accepted mode.
     * <p>
     * In asynchronous fusion, upstream values may become available to {@link #poll()} eventually.
     * Upstream signals onError() and onComplete() as usual but onNext may not actually contain
     * the upstream value but have {@code null} instead. Downstream should treat such onNext as indication
     * that {@link #poll()} can be called. Note that {@link #poll()} has to be called sequentially
     * (from within a serializing drain-loop). In addition, callers of {@link #poll()} should be
     * prepared to catch exceptions.
     */
    int ASYNC = 2;

    /**
     * Request any of the {@link #SYNC} or {@link #ASYNC} modes.
     */
    int ANY = SYNC | ASYNC;

    /**
     * Used in binary or combination with the other constants as an input to {@link #requestFusion(int)}
     * indicating that the {@link #poll()} will be called behind an asynchronous boundary and thus
     * may change the non-trivial computation locations attached to the {@link #poll()} chain of
     * fused operators.
     * <p>
     * For example, fusing map() and observeOn() may move the computation of the map's function over to
     * the thread run after the observeOn(), which is generally unexpected.
     */
    int BOUNDARY = 4;

    /**
     * Request a fusion mode from the upstream.
     * <p>
     * This should be called before {@code onSubscribe} returns.
     * <p>
     * Calling this method multiple times or after {@code onSubscribe} finished is not allowed
     * and may result in undefined behavior.
     * <p>
     *
     * @param mode the requested fusion mode, allowed values are {@link #SYNC}, {@link #ASYNC},
     *             {@link #ANY} combined with {@link #BOUNDARY} (e.g., {@code requestFusion(SYNC | BOUNDARY)}).
     * @return the established fusion mode: {@link #NONE}, {@link #SYNC}, {@link #ASYNC}.
     */
    int requestFusion(int mode);
}
