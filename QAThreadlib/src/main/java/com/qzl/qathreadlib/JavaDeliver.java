package com.qzl.qathreadlib;

import java.util.concurrent.Executor;


final class JavaDeliver implements Executor {

    private static JavaDeliver instance = new JavaDeliver();

    static JavaDeliver getInstance() {
        return instance;
    }

    @Override
    public void execute(Runnable runnable) {
        runnable.run();
    }
}
