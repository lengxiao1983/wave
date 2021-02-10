package com.wave.benchmark;

import com.wave.benchmark.impl.ColumnNode;
import com.wave.benchmark.impl.InputNode;
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
        InputNode inputNode = new InputNode();
        inputNode.setColumnName("a");
        ColumnNode bCol = new ColumnNode();
        bCol.setColumnName("b");
        bCol.setCost(5L);
        inputNode.setExpr(expr);
        inputNode.setNextNode(bCol);

        ColumnNode cCol = new ColumnNode();
        cCol.setColumnName("c");
        cCol.setCost(6L);

        bCol.setExpr(expr);
        bCol.setNextNode(cCol);

        return null;
    }

    public static void main(String[] args) {
        String exprString = "a>1 and b<2";
        Integer runTimes = 10;
        ComputeNode computeNode = createComputeNode(exprString);
        BaseTestRunner baseTestRunner = new BaseTestRunner();
        baseTestRunner.setRootNode(computeNode);
        baseTestRunner.setRunTimes(runTimes);
        baseTestRunner.run();

        OptimizeTestRunner optimizeTestRunner = new OptimizeTestRunner();
        optimizeTestRunner.setRootNode(computeNode);
        optimizeTestRunner.setRunTimes(runTimes);
        optimizeTestRunner.run();
    }
}
