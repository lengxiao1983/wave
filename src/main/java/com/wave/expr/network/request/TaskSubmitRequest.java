package com.wave.expr.network.request;

import com.wave.expr.network.AbstractHandler;
import com.wave.expr.network.AbstractMessage;
import com.wave.expr.network.AbstractSerialize;
import com.wave.expr.network.response.AbstractResponse;
import com.wave.expr.network.response.TaskSubmitResponse;

import java.io.IOException;

/**
 * @author liqiu.qlq
 */
public class TaskSubmitRequest extends AbstractMessage {
    private String inParams;

    public TaskSubmitRequest() {

    }

    public TaskSubmitRequest inParams(String inParams) {
        this.inParams = inParams;
        return this;
    }

    public String getInParams() {
        return inParams;
    }

    public void setInParams(String inParams) {
        this.inParams = inParams;
    }

    @Override
    public AbstractHandler getHandle() {
        return new AbstractHandler<TaskSubmitRequest, TaskSubmitResponse>() {
            @Override
            public TaskSubmitResponse handle(TaskSubmitRequest param) {
                TaskSubmitResponse response = new TaskSubmitResponse();
                response.setOutParams("handle response:" + param.desc());
                return response;
            }
        };
    }

    @Override
    public RequestType getRequestType() {
        return RequestType.TASK_SUBMIT_REQUEST;
    }

    @Override
    public AbstractSerialize getSerializer() {
        return new AbstractSerialize<TaskSubmitRequest>() {
            @Override
            public byte[] serialize(TaskSubmitRequest object) throws IOException {
                return super.serialize(object);
            }

            @Override
            public TaskSubmitRequest deSerialize(byte[] bytes) {
                return super.deSerialize(bytes);
            }
        };
    }

    /**
     * 返回response序列化对象
     * @return
     */
    @Override
    public AbstractSerialize getResponseSerializer() {
        return new AbstractSerialize<TaskSubmitResponse>() {
            @Override
            public byte[] serialize(TaskSubmitResponse object) throws IOException {
                return super.serialize(object);
            }

            @Override
            public TaskSubmitResponse deSerialize(byte[] bytes) {
                return super.deSerialize(bytes);
            }
        };
    }

    @Override
    public String desc() {
        return "inParams:" + inParams;
    }

}
