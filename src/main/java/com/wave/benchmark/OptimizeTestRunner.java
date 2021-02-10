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
            if (Boolean.parseBoolean(String.valueOf(rootNode.getExpr().tryCompute(row)))
                    || rootNode.getExpr().tryCompute(row).equals(UNKNOWN_RESULT)) {
                fetchInner(rootNode.getNextNode(), row);
            }
        }

        return Boolean.parseBoolean(String.valueOf(rootNode.getExpr().computer(row)));
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
