package com.wave.benchmark.impl;

import com.alibaba.fastjson.JSON;
import com.wave.benchmark.ComputeNode;
import com.wave.expr.value.WaveRow;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author liqiu.qlq
 */
@Data
@Slf4j
public class InputNode extends ComputeNode {
    /**
     * 初始字段
     */
    private String columnName;

    @Override
    public WaveRow fetch(WaveRow row) {
        if (row != null) {
            return row;
        }
        WaveRow r = new WaveRow();
        r.put(columnName, new Random().nextInt(100) * 1.0D);
        return r;
    }
}
