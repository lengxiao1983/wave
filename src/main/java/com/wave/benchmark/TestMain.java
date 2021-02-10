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
        cCol.setExpr(expr);

        bCol.setExpr(expr);
        bCol.setNextNode(cCol);

        return inputNode;
    }

    public static ComputeNode findLeafNode(ComputeNode node) {
        ComputeNode curNode = node;
        ComputeNode nextNode = node.getNextNode();
        while (nextNode != null) {
            curNode = nextNode;
            nextNode = nextNode.getNextNode();
        }
        return curNode;
    }

    public static void main(String[] args) {
        String exprString = "a>0 and b<200 and c<300";
        Integer runTimes = 100;
        ComputeNode baseTestNode = createComputeNode(exprString);
        BaseTestRunner baseTestRunner = new BaseTestRunner();
        baseTestRunner.setRootNode(baseTestNode);
        baseTestRunner.setLeafNode(findLeafNode(baseTestNode));
        baseTestRunner.setRunTimes(runTimes);
        baseTestRunner.setName("baseTestRunner");

        ComputeNode optimizeTestNode = createComputeNode(exprString);
        OptimizeTestRunner optimizeTestRunner = new OptimizeTestRunner();
        optimizeTestRunner.setRootNode(optimizeTestNode);
        optimizeTestRunner.setLeafNode(findLeafNode(optimizeTestNode));
        optimizeTestRunner.setRunTimes(runTimes);
        optimizeTestRunner.setName("optimizeTestRunner");

        baseTestRunner.start();
        optimizeTestRunner.start();

    }
}
