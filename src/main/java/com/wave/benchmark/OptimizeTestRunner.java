package com.wave.benchmark;

import com.wave.expr.AbstractExpr;
import com.wave.expr.value.WaveRow;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.wave.expr.AbstractExpr.UNKNOWN_RESULT;

/**
 * @author liqiu.qlq
 */
@Slf4j
@Data
public class OptimizeTestRunner extends AbstractRunner{

    @Override
    public boolean runInner() {
        ComputeNode rootNode = getRootNode();
        WaveRow row = rootNode.fetch(null);
        if (rootNode.getNextNode() != null) {
            Object tryResult = rootNode.getExpr().tryCompute(row);
            Boolean result = Boolean.parseBoolean(String.valueOf(tryResult));
            if (tryResult.equals(UNKNOWN_RESULT)) {
                fetchInner(rootNode.getNextNode(), row);
            } else {
                return result;
            }
        }

        return Boolean.parseBoolean(String.valueOf(getLeafNode().getExpr().computer(row)));
    }

    private void fetchInner(ComputeNode node, WaveRow row) {
        WaveRow resultRow = node.fetch(row);
        if (node.getNextNode() != null) {
            if (Boolean.parseBoolean(String.valueOf(node.getExpr().tryCompute(row)))
                    || node.getExpr().tryCompute(row).equals(UNKNOWN_RESULT)) {
                fetchInner(node.getNextNode(), resultRow);
            }
        }
    }
}
