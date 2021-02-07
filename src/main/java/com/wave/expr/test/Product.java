package com.wave.expr.test;

import com.wave.expr.master.TasksubmitRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author shkstart
 * @create 2021-01-28 21:21
 */
public class Product extends MyThread {

    private BlockingQueue<Map<String, Double>> queue;



    public Product(Integer threadIndex) {
        super(threadIndex);
    }

    public Product queue(BlockingQueue<Map<String, Double>> queue) {
        this.queue = queue;//建立一个队列。
        return this;
    }


    public void runInner() throws Exception {//启动线程，往队列里放入数据

        Map<String, Double> row = new HashMap<String, Double>();
        row.put("a", new Random().nextInt(10) * 1.0d);
        row.put("b", new Random().nextInt(10) * 1.0d);
        row.put("c", new Random().nextInt(10) * 1.0d);
        if (queue.size() > 100) {//如果队列里数据超过100，显示数据过多。？？？
            System.out.println("product thread: " + threadIndex + " queue size:" + queue.size() + " too more!");
        }
        queue.add(row);
        System.out.println("product thread: " + threadIndex + " put row:" + row.toString());
    }
}
