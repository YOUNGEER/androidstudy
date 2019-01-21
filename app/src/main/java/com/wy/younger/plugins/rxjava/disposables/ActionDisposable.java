package com.wy.younger.plugins.rxjava.disposables;

import com.wy.younger.plugins.rxjava.internal.util.ExceptionHelper;
import com.wy.younger.plugins.rxjava.plugins.functions.Action;

public final class ActionDisposable extends ReferenceDisposable<Action> {

    ActionDisposable(Action value) {
        super(value);
    }

    @Override
    protected void onDisposed(Action value) {
        try {
            value.run();
        } catch (Throwable ex) {
            throw ExceptionHelper.wrapOrThrow(ex);
        }
    }
}
