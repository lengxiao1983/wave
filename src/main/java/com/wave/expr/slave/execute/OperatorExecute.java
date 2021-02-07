package com.wave.expr.slave.execute;


/**
 * @author liqiu.qlq
 */
public class OperatorExecute {
    private static final OperatorExecute instance = new OperatorExecute();
    private Thread[] threads;
    private static final int THREAD_NUM = 2;

    OperatorExecute() {
    }



    public void start() {
        threads = new Thread[THREAD_NUM];
        for (int i=0; i<THREAD_NUM; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    System.out.println("execute thread start...");
                    PriorityOperatorRunner runner = null;
                    try {
                        while (true) {
                            runner = PriorityOperatorQueue.get().getQueue().take();
                            if (runner == null) {
                                continue;
                            }
                            runner.run();
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        if (runner != null) {
                            PriorityOperatorQueue.get().add(runner);
                        }
                    }
                }
            });
        }
    }
}
