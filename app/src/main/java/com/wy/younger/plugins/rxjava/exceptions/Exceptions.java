package com.wy.younger.plugins.rxjava.exceptions;

import com.wy.younger.plugins.rxjava.annotations.NonNull;
import com.wy.younger.plugins.rxjava.internal.util.ExceptionHelper;

public final class Exceptions {

    private Exceptions() {
        throw new IllegalStateException("No instances!");
    }

    @NonNull
    public static RuntimeException propagate(@NonNull Throwable t) {
        /*
         * The return type of RuntimeException is a trick for code to be like this:
         *
         * throw Exceptions.propagate(e);
         *
         * Even though nothing will return and throw via that 'throw', it allows the code to look like it
         * so it's easy to read and understand that it will always result in a throw.
         */
        throw ExceptionHelper.wrapOrThrow(t);
    }


    public static void throwIfFatal(@NonNull Throwable t) {
        // values here derived from https://github.com/ReactiveX/RxJava/issues/748#issuecomment-32471495
        if (t instanceof VirtualMachineError) {
            throw (VirtualMachineError) t;
        } else if (t instanceof ThreadDeath) {
            throw (ThreadDeath) t;
        } else if (t instanceof LinkageError) {
            throw (LinkageError) t;
        }
    }
}
