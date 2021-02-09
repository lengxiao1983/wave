package com.wave.slave.operator;

import com.alibaba.fastjson.JSON;
import com.wave.slave.AbstractOperator;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author liqiu.qlq
 */
@Slf4j
public class InputOperator extends AbstractOperator {

    public Map<String, Double> dataSource() {
        try {
            TimeUnit.SECONDS.sleep(1);
            Map<String, Double> row = new HashMap<String, Double>();
            row.put("a", new Random().nextInt(10) * 1.0D);
            row.put("b", new Random().nextInt(10) * 1.0D);
            row.put("c", new Random().nextInt(10) * 1.0D);
            log.info("InputOperator create row:{}", JSON.toJSONString(row));
            return row;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Map<String, Double> compute(Map<String, Double> noNeed/** 产生数据 */) {
        return null;
    }
}
