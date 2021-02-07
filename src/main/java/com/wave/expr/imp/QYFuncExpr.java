package com.wave.expr.imp;

import com.wave.expr.AbstractExpr;
import com.wave.expr.AbstractExprFactory;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2021-01-27 22:20
 */
public class QYFuncExpr extends AbstractExpr {
    private final static String op = "QY";
    public QYFuncExpr() {
        super();
    }
    public String op() {
        return op;
    }

    public Object computer(Map<String, Double> row) {
        List<AbstractExpr> params = getParams();
        if (params == null) {
            return -999d;
        }
        double ret = 0.0;
        for (AbstractExpr expr : params) {
            ret += 1.0d;
        }
        return ret;
    }

    public Object tryCompute(Map<String, Double> curRow) {
        return null;
    }

    @Override
    public AbstractExprFactory<QYFuncExpr> getFactory() {
        return new AbstractExprFactory() {
            @Override
            public AbstractExpr newInstance() {
                return new QYFuncExpr();
            }
        };
    }
}
