package com.wave.expr.master.plan;

import com.wave.expr.slave.OperatorFactory;
import com.wave.expr.slave.factory.InputOperatorFactory;

/**
 * @author liqiu.qlq
 */
public class InputPlanNode extends PlanNode {
    /**
     * 返回算子工厂对象
     * @return
     */
    @Override
    public OperatorFactory getFactory() {
        return new InputOperatorFactory();
    }
}
