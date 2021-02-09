package com.wave.slave;

import com.wave.expr.value.WaveRow;

import java.util.Map;

/**
 * @author liqiu.qlq
 */
public abstract class AbstractOperator {

    AbstractOperator operator;

    public AbstractOperator getOperator() {
        return operator;
    }

    public void setOperator(AbstractOperator operator) {
        this.operator = operator;
    }

    /**
     * 计算数据
     * @param row
     * @return
     */
    public abstract Object compute(WaveRow row);
}
