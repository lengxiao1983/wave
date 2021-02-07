package com.wave.expr.slave.factory;

import com.wave.expr.exception.WaveException;
import com.wave.expr.master.plan.ExprPlanNode;
import com.wave.expr.master.plan.PlanNode;
import com.wave.expr.slave.OperatorFactory;
import com.wave.expr.slave.operator.ExprOperator;

/**
 * @author liqiu.qlq
 */
public class ExprOperatorFactory extends OperatorFactory<ExprOperator> {

    @Override
    public ExprOperator offer(PlanNode planNode) {
        if (planNode instanceof ExprPlanNode) {
            ExprPlanNode exprPlanNode = (ExprPlanNode) planNode;
            ExprOperator exprOperator = new ExprOperator();
            exprOperator.setExprString(exprPlanNode.getExprString());
            exprOperator.setExpr(exprPlanNode.getExpr());
            return exprOperator;
        } else {
            throw new WaveException("planNode not match");
        }
    }
}
