package com.wave.benchmark;

import com.wave.expr.AbstractExpr;
import com.wave.expr.parse.ExprParseUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liqiu.qlq
 */
@Slf4j
public class TestMain {

    /**
     * 生产测试ComputeNode
     * @param exprString
     * @return
     */
    public static ComputeNode createComputeNode(String exprString) {
        AbstractExpr expr = ExprParseUtils.parse(exprString);
        return null;
    }

    public static void main(String[] args) {
        String exprString = "a>1 and b<2";
        Integer runTimes = 10;
        ComputeNode computeNode = createComputeNode(exprString);
        BaseTestRunner baseTestRunner = new BaseTestRunner();
        baseTestRunner.setNodeLink(computeNode);
        baseTestRunner.setRunTimes(runTimes);
        baseTestRunner.run();

        OptimizeTestRunner optimizeTestRunner = new OptimizeTestRunner();
        optimizeTestRunner.setNodeLink(computeNode);
        optimizeTestRunner.setRunTimes(runTimes);
        optimizeTestRunner.run();
    }
}
