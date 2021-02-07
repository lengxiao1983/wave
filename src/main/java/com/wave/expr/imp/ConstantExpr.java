package com.wave.expr.imp;

import com.wave.expr.AbstractExpr;
import com.wave.expr.AbstractExprFactory;
import com.wave.expr.ExprFactory;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2021-01-26 21:49
 */
public class ConstantExpr extends AbstractExpr {

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    private Double value;

    public ConstantExpr() {
        super();
    }

    public ConstantExpr(Double value) {
        super();
        this.value = value;
    }

    public String op() {
        return "CONSTANT";
    }

    public Object computer(Map<String, Double> row) {
        return value;
    }

    public Object tryCompute(Map<String, Double> curRow) {
        return null;
    }
    @Override
    public AbstractExprFactory<ConstantExpr> getFactory() {
        return new AbstractExprFactory() {
            @Override
            public AbstractExpr newInstance() {
                return new ConstantExpr();
            }
        };
    }
}
