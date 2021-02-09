package com.wave.benchmark;

import com.wave.expr.AbstractExpr;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liqiu.qlq
 */
@Slf4j
@Data
public class OptimizeTestRunner {
    private String exprString;
    private AbstractExpr expr;
}
