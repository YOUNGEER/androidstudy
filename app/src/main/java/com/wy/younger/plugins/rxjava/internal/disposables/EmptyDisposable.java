package com.wy.younger.plugins.rxjava.internal.disposables;

import com.wy.younger.plugins.rxjava.CompletableObserver;
import com.wy.younger.plugins.rxjava.MaybeObserver;
import com.wy.younger.plugins.rxjava.Observer;
import com.wy.younger.plugins.rxjava.SingleObserver;
import com.wy.younger.plugins.rxjava.internal.fuseable.QueueDisposable;

/**
 * @package:com.wy.younger.plugins.rxjava.internal.disposables
 * @data on:2019/1/22 14:05
 * author:YOUNG
 * desc:TODO
 */
public enum EmptyDisposable implements QueueDisposable<Object> {
    /**
     * Since EmptyDisposable implements QueueDisposable and is empty,
     * don't use it in tests and then signal onNext with it;
     * use Disposables.empty() instead.
     */
    INSTANCE,
    /**
     * An empty disposable that returns false for isDisposed.
     */
    NEVER;

    @Override
    public void dispose() {

    }


    public static void complete(Observer<?> observer) {
        observer.onSubscribe(INSTANCE);
        observer.onComplete();
    }

    public static void complete(MaybeObserver<?> observer) {
        observer.onSubscribe(INSTANCE);
        observer.onComplete();
    }

    public static void error(Throwable e, Observer<?> observer) {
        observer.onSubscribe(INSTANCE);
        observer.onError(e);
    }

    public static void complete(CompletableObserver observer) {
        observer.onSubscribe(INSTANCE);
        observer.onComplete();
    }

    public static void error(Throwable e, CompletableObserver observer) {
        observer.onSubscribe(INSTANCE);
        observer.onError(e);
    }

    public static void error(Throwable e, SingleObserver<?> observer) {
        observer.onSubscribe(INSTANCE);
        observer.onError(e);
    }

    public static void error(Throwable e, MaybeObserver<?> observer) {
        observer.onSubscribe(INSTANCE);
        observer.onError(e);
    }


    @Override
    public boolean isDisposed() {
        return this == INSTANCE;
    }

    @Override
    public int requestFusion(int mode) {
        return mode & ASYNC;
    }

    @Override
    public boolean offer(Object value) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override
    public boolean offer(Object v1, Object v2) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override
    public Object poll() throws Exception {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public void clear() {

    }
}
