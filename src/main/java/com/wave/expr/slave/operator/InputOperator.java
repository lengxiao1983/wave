package com.wave.expr.slave.operator;

import com.wave.expr.slave.AbstractOperator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author liqiu.qlq
 */
public class InputOperator extends AbstractOperator {

    public Map<String, Double> dataSource() {
        Map<String, Double> row = new HashMap<String, Double>();
        row.put("a", new Random().nextInt(10) * 1.0D);
        row.put("b", new Random().nextInt(10) * 1.0D);
        row.put("c", new Random().nextInt(10) * 1.0D);
        return row;
    }

    @Override
    public Map<String, Double> compute(Map<String, Double> noNeed/** 产生数据 */) {
        return null;
    }
}
