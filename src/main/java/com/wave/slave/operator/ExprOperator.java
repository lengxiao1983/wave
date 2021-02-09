package com.wave.slave.operator;

import com.alibaba.fastjson.JSON;
import com.wave.expr.AbstractExpr;
import com.wave.expr.value.WaveRow;
import com.wave.slave.AbstractOperator;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author liqiu.qlq
 */
@Slf4j
public class ExprOperator extends AbstractOperator{
    private String exprString;
    private AbstractExpr expr;

    @Override
    public Object compute(WaveRow row) {
        boolean result = Boolean.parseBoolean(String.valueOf(expr.computer(row)));
        log.info("ExprOperator compute expr:{} row:{} result:{}", exprString, JSON.toJSONString(row), result);
        return result;
    }

    public String getExprString() {
        return exprString;
    }

    public void setExprString(String exprString) {
        this.exprString = exprString;
    }

    public AbstractExpr getExpr() {
        return expr;
    }

    public void setExpr(AbstractExpr expr) {
        this.expr = expr;
    }
}
