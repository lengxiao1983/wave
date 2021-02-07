package com.wave.expr.imp;

import com.wave.expr.AbstractExpr;
import com.wave.expr.AbstractExprFactory;
import com.wave.expr.ExprFactory;

import java.util.Map;

/**
 * @author shkstart
 * @create 2021-01-26 21:51
 */
public class ColumnExpr extends AbstractExpr {
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    private String columnName;

    public ColumnExpr() {
        super();
    }

    public ColumnExpr(String columnName) {
        super();
        this.columnName = columnName;
    }

    public String op() {
        return "COLUMN";
    }

    public Object computer(Map<String, Double> row) {
        return row.get(columnName);
    }

    public Object tryCompute(Map<String, Double> curRow) {
        return null;
    }
    @Override
    public AbstractExprFactory<ColumnExpr> getFactory() {
        return new AbstractExprFactory() {
            @Override
            public AbstractExpr newInstance() {
                return new ColumnExpr();
            }
        };
    }
}
