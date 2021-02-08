package com.wave.slave.execute;

import com.wave.common.exception.WaveException;
import com.wave.slave.AbstractOperator;
import com.wave.slave.operator.InputOperator;

import java.util.Map;

/**
 * @author liqiu.qlq
 */
public class PriorityOperatorRunner {
    private AbstractOperator operator;

    public AbstractOperator getOperator() {
        return operator;
    }

    public void setOperator(AbstractOperator operator) {
        this.operator = operator;
    }

    public Object run() {
        if (! (operator instanceof InputOperator)) {
            throw new WaveException();
        }
        InputOperator inputOperator = (InputOperator) operator;
        Map<String, Double> row = inputOperator.dataSource();
        if (inputOperator.getOperator() != null) {
            return runInner(inputOperator.getOperator(), row);
        } else {
            return row;
        }
    }

    private Object runInner(AbstractOperator operator, Map<String, Double> row) {
        Object obj = operator.compute(row);
        if (operator.getOperator() == null) {
            return obj;
        } else {
            return runInner(operator.getOperator(), (Map<String, Double>) obj);
        }
    }
}
