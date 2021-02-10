package com.wave.benchmark;

import com.wave.expr.AbstractExpr;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liqiu.qlq
 */
@Slf4j
@Data
public abstract class AbstractRunner implements Runnable {
    /**
     * 运行次数
     */
    private Integer runTimes;

    /**
     * 执行计划
     */
    private ComputeNode nodeLink;


    /**
     * 内容逻辑
     */
    public abstract void runInner();

    public void run() {

    }
}
