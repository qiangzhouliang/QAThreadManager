package com.qzl.qathreadlib;

/**
 * A callback interface to notify user that the task's status.
 */
public interface Callback {


    void onError (String threadName, Throwable t);


    void onCompleted (String threadName);


    void onStart (String threadName);
}
