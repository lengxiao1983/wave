package com.wave.expr.slave.execute;

import com.wave.expr.exception.WaveException;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author liqiu.qlq
 */
public class PriorityOperatorQueue {
    private static final PriorityOperatorQueue instance = new PriorityOperatorQueue();

    public static PriorityOperatorQueue get() {
        return instance;
    }

    private static final PriorityBlockingQueue<PriorityOperatorRunner> queue
            = new PriorityBlockingQueue<PriorityOperatorRunner>(100);

    public PriorityBlockingQueue<PriorityOperatorRunner> getQueue() {
        return queue;
    }

    public void add(PriorityOperatorRunner runner) {
        if (queue.size() > 100) {
            throw new WaveException("queue element too big");
        }
        queue.add(runner);
    }
}
