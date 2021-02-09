package com.wave.slave.operator;

import com.alibaba.fastjson.JSON;
import com.wave.expr.value.WaveRow;
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

    public WaveRow dataSource() {
        try {
            TimeUnit.SECONDS.sleep(1);
            WaveRow row = new WaveRow();
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
    public Map<String, Double> compute(WaveRow noNeed/** 产生数据 */) {
        return null;
    }
}
