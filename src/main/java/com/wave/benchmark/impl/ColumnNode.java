package com.wave.benchmark.impl;

import com.wave.benchmark.ComputeNode;
import com.wave.common.exception.WaveException;
import com.wave.expr.value.WaveRow;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author liqiu.qlq
 */
@Data
@Slf4j
public class ColumnNode extends ComputeNode {
    /**
     * 获取字段消耗
     */
    private Long cost;

    /**
     * 字段名称
     */
    private String columnName;

    @Override
    public WaveRow fetch(WaveRow row) {
        try {
            TimeUnit.MILLISECONDS.sleep(cost);
            if (row != null) {
                row.put(columnName, new Random().nextInt(100) * 1.0D);
                return row;
            }
            WaveRow r = new WaveRow();
            r.put(columnName, new Random().nextInt(100) * 1.0D);
            return r;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new WaveException(e);
        }
    }
}
