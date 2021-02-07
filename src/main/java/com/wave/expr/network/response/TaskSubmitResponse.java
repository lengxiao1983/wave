package com.wave.expr.network.response;


/**
 * @author liqiu.qlq
 */
public class TaskSubmitResponse extends AbstractResponse {
    private String outParams;

    public String getOutParams() {
        return outParams;
    }

    public void setOutParams(String outParams) {
        this.outParams = outParams;
    }

    @Override
    public String desc() {
        return "outParams:" + outParams;
    }
}
