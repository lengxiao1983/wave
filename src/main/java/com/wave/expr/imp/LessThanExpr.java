package com.wave.expr.imp;

import com.wave.expr.AbstractExpr;
import com.wave.expr.AbstractExprFactory;
import com.wave.expr.value.WaveRow;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2021-01-28 9:48
 */
public class LessThanExpr extends AbstractExpr {
    private final static String op = "<";

    public LessThanExpr() {
        super();
    }

    public  String op() {
        return op;
    }

    public Object computer(WaveRow row) {
        List<AbstractExpr> params = getParams();
        AbstractExpr left = params.get(0);
        AbstractExpr right = params.get(1);
        Double leftVal = Double.valueOf(String.valueOf(left.computer(row)));
        Double rightVal = Double.valueOf(String.valueOf(right.computer(row)));
        return leftVal < rightVal;
    }

    public Object tryCompute(WaveRow curRow) {
        return null;
    }
    @Override
    public AbstractExprFactory<LessThanExpr> getFactory() {
        return new AbstractExprFactory() {
            @Override
            public AbstractExpr newInstance() {
                return new LessThanExpr();
            }
        };
    }
}
