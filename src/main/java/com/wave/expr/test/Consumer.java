package com.wave.expr.test;

import com.wave.expr.AbstractExpr;
import com.wave.expr.parse.ExprParseUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author shkstart
 * @create 2021-01-28 21:21
 */
public class Consumer extends MyThread {
    private BlockingQueue<Map<String, Double>> queue;
    private String exprString;
    private AbstractExpr expr;

    public Consumer(Integer threadIndex, BlockingQueue<Map<String, Double>> queue, String exprString) {
        super(threadIndex);
        this.queue = queue;
        this.exprString = exprString;
        this.expr = ExprParseUtils.parse(exprString);
    }


    public void runInner() throws Exception {//启动线程消费队列
        while (true) {
            Map<String, Double> row = queue.take();
            if (row != null) {
                Object result = expr.computer(row);
                System.out.println("consumer thread:" + threadIndex
                        + " row:" + row.toString()
                        + " expr:" + exprString
                        + " result:" + result);
            }
        }
    }
}
