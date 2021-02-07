package com.wave.expr.imp;

import com.wave.expr.AbstractExpr;
import com.wave.expr.AbstractExprFactory;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2021-01-26 21:45
 */
public class SubtractionExpr extends AbstractExpr {
    private final static String op = "-";
    public SubtractionExpr() {
        super();
    }
    public String op() {
        return op;
    }

    public Object computer(Map<String, Double> row) {
        List<AbstractExpr> params = getParams();
        if (params == null) {
            return 0.0;
        }
        double ret = 0.0;
        for (AbstractExpr expr : params) {
            ret -= Double.valueOf(String.valueOf(expr.computer(row)));
        }
        return ret;
    }

    public Object tryCompute(Map<String, Double> curRow) {
        return null;
    }

    @Override
    public AbstractExprFactory<SubtractionExpr> getFactory() {
        return new AbstractExprFactory() {
            @Override
            public AbstractExpr newInstance() {
                return new SubtractionExpr();
            }
        };
    }
}
