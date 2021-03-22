package com.qzl.qathreadlib;

import java.util.concurrent.Callable;

final class CallableWrapper<T> implements Callable<T> {
    private String name;
    private Callback callback;
    private Callable<T> proxy;

    CallableWrapper(Configs configs, Callable<T> proxy) {
        this.name = configs.name;
        this.proxy = proxy;
        this.callback = new CallbackDelegate(configs.callback, configs.deliver, configs.asyncCallback);
    }

    @Override
    public T call() throws Exception {
        Tools.resetThread(Thread.currentThread(),name,callback);
        if (callback != null) {
            callback.onStart(name);
        }

        // avoid NullPointException
        T t = proxy == null ? null : proxy.call();
        if (callback != null)  {
            callback.onCompleted(name);
        }
        return t;
    }
}
