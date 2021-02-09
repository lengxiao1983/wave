package com.wave.expr.imp;

import com.wave.expr.AbstractExpr;
import com.wave.expr.AbstractExprFactory;

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

    public Object computer(Map<String, Double> row) {
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

    public Object tryCompute(Map<String, Double> curRow) {

        List<AbstractExpr> childs = getParams();
        boolean hasExistUnknownColumn = true;
        for (AbstractExpr expr : childs) {
            if ((expr instanceof ColumnExpr && expr.computer(curRow) == null)
                    || UNKNOWN_RESULT.equals(expr.computer(curRow))) {
                hasExistUnknownColumn = false;
            }
            if ( Boolean.parseBoolean(String.valueOf(expr.computer(curRow)))) {
                return true;
            }
        }
        if (!hasExistUnknownColumn) {
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
