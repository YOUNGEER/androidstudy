package com.wy.younger.plugins.rxjava.exceptions;

import com.wy.younger.plugins.rxjava.annotations.NonNull;

public final class OnErrorNotImplementedException extends RuntimeException {

    private static final long serialVersionUID = -6298857009889503852L;

    /**
     * Customizes the {@code Throwable} with a custom message and wraps it before it
     * is signalled to the {@code RxJavaPlugins.onError()} handler as {@code OnErrorNotImplementedException}.
     *
     * @param message
     *          the message to assign to the {@code Throwable} to signal
     * @param e
     *          the {@code Throwable} to signal; if null, a NullPointerException is constructed
     */
    public OnErrorNotImplementedException(String message, @NonNull Throwable e) {
        super(message, e != null ? e : new NullPointerException());
    }

    /**
     * Wraps the {@code Throwable} before it
     * is signalled to the {@code RxJavaPlugins.onError()}
     * handler as {@code OnErrorNotImplementedException}.
     *
     * @param e
     *          the {@code Throwable} to signal; if null, a NullPointerException is constructed
     */
    public OnErrorNotImplementedException(@NonNull Throwable e) {
        this("The exception was not handled due to missing onError handler in the subscribe() method call. Further reading: https://github.com/ReactiveX/RxJava/wiki/Error-Handling | " + e, e);
    }
}
