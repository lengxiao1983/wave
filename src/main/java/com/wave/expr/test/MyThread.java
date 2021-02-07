package com.wave.expr.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author shkstart
 * @create 2021-01-28 21:37
 */
public abstract class MyThread implements Runnable {
    protected Thread thread;
    protected Integer threadIndex;

    public MyThread(Integer threadIndex) {
        this.threadIndex = threadIndex;
    }

    public synchronized void start() {
        if (thread != null) {
            thread.interrupt();
        }
        thread = new Thread(this);
        thread.start();
    }

    public abstract void runInner() throws Exception;

    public void run() {
        try {
            while (true) {
                runInner();
                TimeUnit.SECONDS.sleep(3);
            }
        } catch (Throwable t) {
            throw new RuntimeException(t);
        } finally {
            start();
        }

    }
}
