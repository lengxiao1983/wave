package com.wave.expr.slave.operator;

import com.wave.expr.AbstractExpr;
import com.wave.expr.slave.AbstractOperator;

import java.util.Map;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author liqiu.qlq
 */
public class ExprOperator extends AbstractOperator{
    private String exprString;
    private AbstractExpr expr;

    @Override
    public Object compute(Map<String, Double> row) {
        return Boolean.parseBoolean(String.valueOf(expr.computer(row)));
    }

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
}
