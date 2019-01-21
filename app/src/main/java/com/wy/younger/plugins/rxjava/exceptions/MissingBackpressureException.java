package com.wy.younger.plugins.rxjava.exceptions;

public final class MissingBackpressureException extends RuntimeException{

    private static final long serialVersionUID = 8517344746016032542L;

    /**
     * Constructs a MissingBackpressureException without message or cause.
     */
    public MissingBackpressureException() {
        // no message
    }

    /**
     * Constructs a MissingBackpressureException with the given message but no cause.
     * @param message the error message
     */
    public MissingBackpressureException(String message) {
        super(message);
    }
}
