package com.wy.younger.plugins.rxjava.exceptions;

public final class UndeliverableException  extends  IllegalStateException{

    private static final long serialVersionUID = 1644750035281290266L;

    /**
     * Construct an instance by wrapping the given, non-null
     * cause Throwable.
     * @param cause the cause, not null
     */
    public UndeliverableException(Throwable cause) {
        super("The exception could not be delivered to the consumer because it has already canceled/disposed the flow or the exception has nowhere to go to begin with. Further reading: https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0#error-handling | " + cause, cause);
    }
}
