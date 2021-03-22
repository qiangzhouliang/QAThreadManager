package com.qzl.qathreadlib;

import java.util.concurrent.Executor;


final class Configs {
    String name;// thread name
    Callback callback;// thread callback
    long delay;// delay time
    Executor deliver;// thread deliver
    AsyncCallback asyncCallback;
}
