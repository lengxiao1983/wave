package com.wave.master.plan;

import com.wave.slave.OperatorFactory;

import java.io.Serializable;

/**
 * @author liqiu.qlq
 */
public abstract class PlanNode implements Serializable {
    private PlanNode planNode;

    public PlanNode getPlanNode() {
        return planNode;
    }

    public void setPlanNode(PlanNode planNode) {
        this.planNode = planNode;
    }

    /**
     * 返回算子工厂对象
     * @return
     */
    public abstract OperatorFactory getFactory();
}
