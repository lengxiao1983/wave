package com.wave.expr.imp;

import com.alibaba.fastjson.JSON;
import com.wave.common.exception.WaveException;
import com.wave.expr.AbstractExpr;
import com.wave.expr.AbstractExprFactory;
import com.wave.expr.value.WaveRow;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2021-01-28 9:48
 */
@Slf4j
public class LessThanExpr extends AbstractExpr {
    private final static String op = "<";

    public LessThanExpr() {
        super();
    }

    public  String op() {
        return op;
    }

    public Object computer(WaveRow row) {
        try {
            List<AbstractExpr> params = getParams();
            AbstractExpr left = params.get(0);
            AbstractExpr right = params.get(1);
            Double leftVal = Double.valueOf(String.valueOf(left.computer(row)));
            Double rightVal = Double.valueOf(String.valueOf(right.computer(row)));
            return leftVal < rightVal;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.error("row:{}", JSON.toJSONString(row));
            throw new WaveException(e);
        }
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
    public AbstractExprFactory<LessThanExpr> getFactory() {
        return new AbstractExprFactory() {
            @Override
            public AbstractExpr newInstance() {
                return new LessThanExpr();
            }
        };
    }
}
