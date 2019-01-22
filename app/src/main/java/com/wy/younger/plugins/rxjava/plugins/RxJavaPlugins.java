package com.wy.younger.plugins.rxjava.plugins;

import com.wy.younger.plugins.rxjava.Observable;
import com.wy.younger.plugins.rxjava.annotations.NonNull;
import com.wy.younger.plugins.rxjava.annotations.Nullable;
import com.wy.younger.plugins.rxjava.exceptions.CompositeException;
import com.wy.younger.plugins.rxjava.exceptions.MissingBackpressureException;
import com.wy.younger.plugins.rxjava.exceptions.OnErrorNotImplementedException;
import com.wy.younger.plugins.rxjava.exceptions.UndeliverableException;
import com.wy.younger.plugins.rxjava.internal.util.ExceptionHelper;
import com.wy.younger.plugins.rxjava.plugins.functions.Consumer;
import com.wy.younger.plugins.rxjava.plugins.functions.Function;

public final class RxJavaPlugins {

    @Nullable
    static volatile Consumer<? super Throwable> errorHandler;

    @Nullable
    static volatile Function<? super Runnable, ? extends Runnable> onScheduleHandler;

    @io.reactivex.annotations.Nullable
    static volatile Function<? super Observable, ? extends Observable> onObservableAssembly;

    @NonNull
    public static <T> Observable<T> onAssembly(@NonNull Observable<T> source) {
        Function<? super Observable, ? extends Observable> f = onObservableAssembly;
        if (f != null) {
            return apply(f, source);
        }
        return source;
    }

    @NonNull
    static <T, R> R apply(@NonNull Function<T, R> f, @NonNull T t) {
        try {
            return f.apply(t);
        } catch (Throwable ex) {
            throw ExceptionHelper.wrapOrThrow(ex);
        }
    }

    public static void onError(@NonNull Throwable error) {
        Consumer<? super Throwable> f = errorHandler;

        if (error == null) {
            error = new NullPointerException("onError called with null. Null values are" +
                    " generally not allowed in 2.x operators and sources.");
        } else {
            if (!isBug(error)) {
                error = new UndeliverableException(error);
            }
        }

        if (f != null) {
            try {
                f.accept(error);
                return;
            } catch (Throwable e) {
                // Exceptions.throwIfFatal(e); TODO decide
                e.printStackTrace(); // NOPMD
                uncaught(e);
            }
        }

        error.printStackTrace(); // NOPMD
        uncaught(error);
    }


    static boolean isBug(Throwable error) {
        // user forgot to add the onError handler in subscribe
        if (error instanceof OnErrorNotImplementedException) {
            return true;
        }
        // the sender didn't honor the request amount
        // it's either due to an operator bug or concurrent onNext
        if (error instanceof MissingBackpressureException) {
            return true;
        }
        // general protocol violations
        // it's either due to an operator bug or concurrent onNext
        if (error instanceof IllegalStateException) {
            return true;
        }
        // nulls are generally not allowed
        // likely an operator bug or missing null-check
        if (error instanceof NullPointerException) {
            return true;
        }
        // bad arguments, likely invalid user input
        if (error instanceof IllegalArgumentException) {
            return true;
        }
        // Crash while handling an exception
        if (error instanceof CompositeException) {
            return true;
        }
        // everything else is probably due to lifecycle limits
        return false;
    }

    static void uncaught(@NonNull Throwable error) {
        Thread currentThread = Thread.currentThread();
        Thread.UncaughtExceptionHandler handler = currentThread.getUncaughtExceptionHandler();
        handler.uncaughtException(currentThread, error);
    }

}
