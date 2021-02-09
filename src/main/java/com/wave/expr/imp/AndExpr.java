package com.wave.expr.imp;

import com.wave.expr.AbstractExpr;
import com.wave.expr.AbstractExprFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2021-01-26 21:26
 */
@Slf4j
public class AndExpr extends AbstractExpr {
    private final static String op = "AND";

    public AndExpr() {
        super();
    }

    @Override
    public  String op() {
        return op;
    }

    @Override
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

    @Override
    public Object tryCompute(Map<String, Double> curRow) {
        List<AbstractExpr> childs = getParams();
        boolean hasExistUnknownColumn = false;
        for (AbstractExpr expr : childs) {
            if ((expr instanceof ColumnExpr && expr.computer(curRow) == null)
                    || UNKNOWN_RESULT.equals(expr.computer(curRow))) {
                hasExistUnknownColumn = true;
            }
            if (! Boolean.parseBoolean(String.valueOf(expr.computer(curRow)))) {
                return false;
            }
        }
        if (hasExistUnknownColumn) {
            return UNKNOWN_RESULT;
        }
        return true;
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
