package com.wave.slave;

import com.wave.master.plan.PlanNode;

/**
 * @author liqiu.qlq
 */
public abstract class OperatorFactory<T extends AbstractOperator> {

    /**
     * 生产算子
     * @param planNode
     * @return
     */
    public abstract T offer(PlanNode planNode);
}
