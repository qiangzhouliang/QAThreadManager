package com.qzl.qathreadlib;

import java.util.concurrent.Executor;

final class CallbackDelegate implements Callback, AsyncCallback {

    private Callback callback;
    private AsyncCallback async;
    private Executor deliver;

    CallbackDelegate(Callback callback, Executor deliver, AsyncCallback async) {
        this.callback = callback;
        this.deliver = deliver;
        this.async = async;
    }

    @Override
    public void onSuccess(final Object o) {
        if (async == null) return;
        deliver.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //noinspection unchecked
                    async.onSuccess(o);
                } catch (Throwable t) {
                    onFailed(t);
                }
            }
        });
    }

    @Override
    public void onFailed(final Throwable t) {
        if (async == null) return;
        deliver.execute(new Runnable() {
            @Override
            public void run() {
                async.onFailed(t);
            }
        });
    }

    @Override
    public void onError(final String name, final Throwable t) {
        onFailed(t);

        if (callback == null) return;
        deliver.execute(new Runnable() {
            @Override
            public void run() {
                callback.onError(name, t);
            }
        });
    }

    @Override
    public void onCompleted(final String name) {
        if (callback == null) return;
        deliver.execute(new Runnable() {
            @Override
            public void run() {
                callback.onCompleted(name);
            }
        });
    }

    @Override
    public void onStart(final String name) {
        if (callback == null) return;
        deliver.execute(new Runnable() {
            @Override
            public void run() {
                callback.onStart(name);
            }
        });
    }
}
