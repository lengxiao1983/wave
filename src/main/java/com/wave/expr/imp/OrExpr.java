package com.wave.expr.imp;

import com.wave.expr.AbstractExpr;
import com.wave.expr.AbstractExprFactory;
import com.wave.expr.value.WaveRow;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2021-01-28 18:58
 */
public class OrExpr extends AbstractExpr {
    private final static String op = "OR";

    public OrExpr() {
        super();
    }

    public  String op() {
        return op;
    }

    public Object computer(WaveRow row) {
        List<AbstractExpr> params = getParams();
        if (params == null) {
            return false;
        }
        for (AbstractExpr expr : params) {
            if (Boolean.parseBoolean(String.valueOf(expr.computer(row)))) {
                return true;
            }
        }
        return false;
    }

    public Object tryCompute(WaveRow curRow) {

        List<AbstractExpr> childs = getParams();
        boolean hasExistUnknownColumn = false;
        for (AbstractExpr expr : childs) {
            if (UNKNOWN_RESULT.equals(expr.tryCompute(curRow))) {
                hasExistUnknownColumn = true;
            }
            if (Boolean.parseBoolean(String.valueOf(expr.tryCompute(curRow)))) {
                return true;
            }
        }
        if (hasExistUnknownColumn) {
            return UNKNOWN_RESULT;
        }
        return false;
    }

    @Override
    public AbstractExprFactory<OrExpr> getFactory() {
        return new AbstractExprFactory() {
            @Override
            public AbstractExpr newInstance() {
                return new OrExpr();
            }
        };
    }
}
