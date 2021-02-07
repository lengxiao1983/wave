package com.wave.expr;

import com.wave.expr.imp.AddExpr;
import com.wave.expr.imp.ColumnExpr;
import com.wave.expr.imp.ConstantExpr;
import com.wave.expr.parse.ExprParseUtils;
import com.wave.expr.test.Consumer;
import com.wave.expr.test.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author shkstart
 * @create 2021-01-26 21:53
 */
public class Test {

    public static void main(String[] args) throws Exception {

        BlockingQueue<Map<String, Double>> queue = new ArrayBlockingQueue<Map<String, Double>>(100);

        // 启动三个生产者，成产数据进队列queue
        for (int i=0; i<3; i++) {
            new Product(i).queue(queue).start();
        }

        new Consumer(0, queue, "(a+b+c) > 10.0").start();
        new Consumer(1, queue, "(a+b+c) > 20.0").start();
        new Consumer(1, queue, "(a+b) < 5.0").start();
    }
}
