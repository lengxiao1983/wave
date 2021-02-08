package com.wave.network.request;

import com.wave.network.AbstractMessage;

/**
 * @author liqiu.qlq
 */

public enum RequestType {
    TASK_SUBMIT_REQUEST(1, new TaskSubmitRequest());

    private int val;
    private AbstractMessage message;

    RequestType(int val, AbstractMessage message) {
        this.val = val;
        this.message = message;
    }

    public int getVal() {
        return val;
    }

    public AbstractMessage getMessage() {
        return message;
    }
}
