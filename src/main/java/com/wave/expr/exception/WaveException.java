package com.wave.expr.exception;


/**
 * @author liqiu.qlq
 */
public class WaveException extends RuntimeException {
    public WaveException() {
        super();
    }

    public WaveException(String msg) {
        super(msg);
    }

    public WaveException(Throwable t) {
        super(t);
    }

}
