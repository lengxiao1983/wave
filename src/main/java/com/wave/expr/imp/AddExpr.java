package com.wave.expr.imp;

import com.wave.expr.AbstractExpr;
import com.wave.expr.AbstractExprFactory;
import com.wave.expr.value.WaveRow;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2021-01-26 21:35
 */
public class AddExpr extends AbstractExpr {
    private final static String op = "+";
    public AddExpr() {
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
            ret += Double.valueOf(String.valueOf(expr.computer(row)));
        }
        return ret;
    }

    public Object tryCompute(WaveRow curRow) {
        return null;
    }

    @Override
    public AbstractExprFactory<AddExpr> getFactory() {
        return new AbstractExprFactory() {
            @Override
            public AbstractExpr newInstance() {
                return new AddExpr();
            }
        };
    }
}
