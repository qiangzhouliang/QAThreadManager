package com.qzl.qathreadlib;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

final class AndroidDeliver implements Executor {

    private static AndroidDeliver instance = new AndroidDeliver();
    private Handler main = new Handler(Looper.getMainLooper());

    static AndroidDeliver getInstance() {
        return instance;
    }

    @Override
    public void execute(final Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
            return;
        }

        main.post(new Runnable() {
            @Override
            public void run() {
                runnable.run();
            }
        });
    }
}
