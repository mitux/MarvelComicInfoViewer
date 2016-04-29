package com.example.a630703.marvelcomicinfoviewer.view;

import android.os.Handler;
import android.os.Looper;

import com.example.executor.PostExecutionThread;

/**
 * Created by a630703 on 27/04/2016.
 */
public class UIThread implements PostExecutionThread {

    private static class LazyHolder {
        private static final UIThread INSTANCE = new UIThread();
    }

    public static UIThread getInstance(){
        return LazyHolder.INSTANCE;
    }

    private final Handler handler;

    private UIThread(){
        this.handler = new android.os.Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        this.handler.post(runnable);
    }
}

