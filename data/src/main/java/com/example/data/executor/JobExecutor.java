package com.example.data.executor;

import com.example.executor.ThreadExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by a630703 on 25/04/2016.
 */
public class JobExecutor implements ThreadExecutor {

    private static class LazyHolder {
        private static final JobExecutor INSTANCE = new JobExecutor();
    }

    public static JobExecutor getInstance(){
        return LazyHolder.INSTANCE;
    }

    private static final int INITIAL_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;

    private static final int KEEP_ALIVE_TIME = 10;

    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    private final BlockingQueue<Runnable> workQueue;

    private final ThreadPoolExecutor threadPoolExecutor;

    private JobExecutor() {
        this.workQueue = new LinkedBlockingQueue<Runnable>();
        this.threadPoolExecutor = new ThreadPoolExecutor(INITIAL_POOL_SIZE,MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,KEEP_ALIVE_TIME_UNIT, this.workQueue);
    }

    @Override
    public void execute(Runnable runnable) {
        this.threadPoolExecutor.execute(runnable);
    }

}
