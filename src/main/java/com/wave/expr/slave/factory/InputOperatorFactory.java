package com.wave.expr.slave.factory;

import com.wave.expr.exception.WaveException;
import com.wave.expr.master.plan.InputPlanNode;
import com.wave.expr.master.plan.PlanNode;
import com.wave.expr.slave.OperatorFactory;
import com.wave.expr.slave.operator.InputOperator;

/**
 * @author liqiu.qlq
 */
public class InputOperatorFactory extends OperatorFactory {
    @Override
    public InputOperator offer(PlanNode planNode) {
        if (planNode instanceof InputPlanNode) {
            InputPlanNode inputPlanNode = (InputPlanNode) planNode;
            InputOperator inputOperator = new InputOperator();
            return inputOperator;
        } else {
            throw new WaveException("planNode not match");
        }
    }
}
