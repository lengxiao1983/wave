package com.wave.benchmark;

import com.wave.expr.AbstractExpr;
import com.wave.expr.value.WaveRow;
import javafx.util.Pair;
import lombok.Data;

/**
 * @author liqiu.qlq
 */
@Data
public abstract class ComputeNode {
    private Pair<AbstractExpr, ComputeNode> nextNode;

    /**
     * 获取字段
     * @param row
     * @return
     */
    public abstract WaveRow fetch(WaveRow row);
}
