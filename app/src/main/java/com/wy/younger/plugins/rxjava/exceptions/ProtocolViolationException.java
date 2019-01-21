package com.wy.younger.plugins.rxjava.exceptions;

public class ProtocolViolationException extends IllegalStateException {

    private static final long serialVersionUID = 1644750035281290266L;

    /**
     * Creates an instance with the given message.
     *
     * @param message the message
     */
    public ProtocolViolationException(String message) {
        super(message);
    }
}
