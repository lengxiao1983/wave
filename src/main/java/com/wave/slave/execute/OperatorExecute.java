package com.wave.slave.execute;


import lombok.extern.slf4j.Slf4j;

/**
 * @author liqiu.qlq
 */
@Slf4j
public class OperatorExecute {
    private static final OperatorExecute instance = new OperatorExecute();
    private Thread[] threads;
    private static final int THREAD_NUM = 1;

    public static OperatorExecute get() {
        return instance;
    }

    OperatorExecute() {
    }



    public void start() {
        log.info("execute start begin");
        threads = new Thread[THREAD_NUM];
        for (int i=0; i<THREAD_NUM; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    log.info("execute thread start...");
                    PriorityOperatorRunner runner = null;
                    try {
                        while (true) {
                            log.info("=====start one loop=====");
                            runner = PriorityOperatorQueue.get().getQueue().take();
                            if (runner == null) {
                                continue;
                            }
                            runner.run();
                            PriorityOperatorQueue.get().add(runner);
                        }
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    } finally {
                        if (runner != null) {
                            PriorityOperatorQueue.get().add(runner);
                        }
                    }
                }
            });
            threads[i].start();
        }
    }
}
