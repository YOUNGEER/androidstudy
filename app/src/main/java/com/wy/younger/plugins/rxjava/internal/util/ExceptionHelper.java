package com.wy.younger.plugins.rxjava.internal.util;

import io.reactivex.exceptions.CompositeException;

import java.util.concurrent.atomic.AtomicReference;

public final class ExceptionHelper {

    private ExceptionHelper() {
        throw new IllegalStateException("No instances!");
    }


    public static RuntimeException wrapOrThrow(Throwable error) {
        if (error instanceof Error) {
            throw (Error)error;
        }
        if (error instanceof RuntimeException) {
            return (RuntimeException)error;
        }
        return new RuntimeException(error);
    }


    public static final Throwable TERMINATED = new Termination();

    public static <T> boolean addThrowable(AtomicReference<Throwable> field, Throwable exception) {
        for (;;) {
            Throwable current = field.get();

            if (current == TERMINATED) {
                return false;
            }

            Throwable update;
            if (current == null) {
                update = exception;
            } else {
                update = new CompositeException(current, exception);
            }

            if (field.compareAndSet(current, update)) {
                return true;
            }
        }
    }


    static final class Termination extends Throwable {

        private static final long serialVersionUID = -4649703670690200604L;

        Termination() {
            super("No further exceptions");
        }

        @Override
        public Throwable fillInStackTrace() {
            return this;
        }
    }

}
