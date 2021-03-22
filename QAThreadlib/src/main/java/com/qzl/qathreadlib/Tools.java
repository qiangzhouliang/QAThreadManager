package com.qzl.qathreadlib;

final class Tools {

    static boolean isAndroid;// Flag: is on android platform

    /**
     * Reset thread name and set a UnCaughtExceptionHandler to wrap callback to notify user when occurs a exception
     * @param thread The thread who should be reset.
     * @param name  non-null, thread name
     * @param callback a callback to notify user.
     */
    static void resetThread(Thread thread, final String name, final Callback callback) {
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                if (callback != null) {
                    callback.onError(name, e);
                }
            }
        });
        thread.setName(name);
    }

    static void sleepThread(long time) {
        if (time <= 0) {
            return;
        }

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread has been interrupted", e);
        }
    }

    static boolean isEmpty(CharSequence data) {
        return data == null || data.length() == 0;
    }

    static {
        try {
            Class.forName("android.os.Build");
            isAndroid = true;
        } catch (Exception e) {
            isAndroid = false;
        }
    }
}