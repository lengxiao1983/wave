package com.wave.master.plan;

import com.wave.expr.AbstractExpr;
import com.wave.slave.OperatorFactory;
import com.wave.slave.factory.ExprOperatorFactory;

/**
 * @author liqiu.qlq
 */
public class ExprPlanNode extends PlanNode {
    private String exprString;
    private AbstractExpr expr;

    public String getExprString() {
        return exprString;
    }

    public void setExprString(String exprString) {
        this.exprString = exprString;
    }

    public AbstractExpr getExpr() {
        return expr;
    }

    public void setExpr(AbstractExpr expr) {
        this.expr = expr;
    }

    @Override
    public OperatorFactory getFactory() {
        return new ExprOperatorFactory();
    }
}
