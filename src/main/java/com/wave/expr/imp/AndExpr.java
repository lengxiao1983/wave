package com.wave.expr.imp;

import com.wave.expr.AbstractExpr;
import com.wave.expr.AbstractExprFactory;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2021-01-26 21:26
 */
public class AndExpr extends AbstractExpr {
    private final static String op = "AND";

    public AndExpr() {
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
            if (! Boolean.parseBoolean(String.valueOf(expr.computer(row)))) {
                return false;
            }
        }
        return true;
    }

    public Object tryCompute(Map<String, Double> curRow) {
        return null;
    }

    @Override
    public AbstractExprFactory<AndExpr> getFactory() {
        return new AbstractExprFactory() {
            @Override
            public AbstractExpr newInstance() {
                return new AndExpr();
            }
        };
    }
}
