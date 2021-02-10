package com.wave.benchmark;

import com.wave.expr.value.WaveRow;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liqiu.qlq
 */
@Slf4j
@Data
public class BaseTestRunner extends AbstractRunner{

    @Override
    public boolean runInner() {
        ComputeNode rootNode = getRootNode();
        WaveRow row = rootNode.fetch(null);
        if (rootNode.getNextNode() != null) {
            fetchNext(rootNode.getNextNode(), row);
        }
        boolean result = Boolean.parseBoolean(String.valueOf(rootNode.getExpr().computer(row)));
        return result;
    }

    private void fetchNext(ComputeNode node, WaveRow row) {
        WaveRow resultRow = node.fetch(row);
        if (node.getNextNode() != null) {
            fetchNext(node.getNextNode(), resultRow);
        }
    }
}
