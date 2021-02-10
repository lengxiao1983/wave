package com.wave.benchmark.impl;

import com.wave.benchmark.ComputeNode;
import com.wave.expr.value.WaveRow;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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
        return row;
    }
}
