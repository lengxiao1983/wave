package com.wave.expr.imp;

import com.wave.expr.AbstractExpr;
import com.wave.expr.AbstractExprFactory;
import com.wave.expr.value.WaveRow;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2021-01-27 15:30
 */
public class MultiplicationExpr extends AbstractExpr {
    private final static String op = "*";
    public MultiplicationExpr() {
        super();
    }
    public String op() {
        return op;
    }

    public Object computer(WaveRow row) {
        List<AbstractExpr> params = getParams();
        if (params == null) {
            return 0.0;
        }
        double ret = 0.0;
        for (AbstractExpr expr : params) {
            ret *= Double.valueOf(String.valueOf(expr.computer(row)));
        }
        return ret;
    }

    public Object tryCompute(WaveRow curRow) {
        List<AbstractExpr> params = getParams();
        for (AbstractExpr expr : params) {
            if (UNKNOWN_RESULT.equals(expr.tryCompute(curRow))) {
                return UNKNOWN_RESULT;
            }
        }
        return computer(curRow);
    }
    @Override
    public AbstractExprFactory<MultiplicationExpr> getFactory() {
        return new AbstractExprFactory() {
            @Override
            public AbstractExpr newInstance() {
                return new MultiplicationExpr();
            }
        };
    }
}
