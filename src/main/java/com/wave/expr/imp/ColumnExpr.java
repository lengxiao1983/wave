package com.wave.expr.imp;

import com.wave.expr.AbstractExpr;
import com.wave.expr.AbstractExprFactory;
import com.wave.expr.ExprFactory;
import com.wave.expr.value.WaveRow;

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

    @Override
    public String op() {
        return "COLUMN";
    }

    @Override
    public Object computer(WaveRow row) {
        return row.get(columnName);
    }

    @Override
    public Object tryCompute(WaveRow curRow) {
        return curRow.get(columnName) == null ? UNKNOWN_RESULT : curRow.get(columnName);
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
