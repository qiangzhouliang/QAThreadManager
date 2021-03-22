package com.qzl.qathreadlib;


public interface AsyncCallback<T> {
    void onSuccess(T t);
    void onFailed(Throwable t);
}
