package com.wave.expr;

import com.wave.expr.value.WaveRow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author shkstart
 * @create 2021-01-26 21:18
 */
public abstract class AbstractExpr implements Serializable {
    public List<AbstractExpr> params = new ArrayList<AbstractExpr>();

    public static final Object UNKNOWN_RESULT = new Object();

    public AbstractExpr() {
    }

    public abstract String op();

    public abstract Object computer(WaveRow row);

    public abstract Object tryCompute(WaveRow curRow);

    public List<AbstractExpr> getParams() {
        return params;
    }

    public void setParams(List<AbstractExpr> params) {
        this.params = params;
    }

    public abstract AbstractExprFactory getFactory();
}
