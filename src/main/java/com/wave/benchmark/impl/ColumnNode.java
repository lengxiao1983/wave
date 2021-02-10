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
        return row;
    }
}
