package com.wave.slave.factory;

import com.wave.common.exception.WaveException;
import com.wave.master.plan.InputPlanNode;
import com.wave.master.plan.PlanNode;
import com.wave.slave.OperatorFactory;
import com.wave.slave.operator.InputOperator;

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
