package com.wave.network.request;

import com.alibaba.fastjson.JSON;
import com.wave.master.plan.ExprPlanNode;
import com.wave.master.plan.PlanNode;
import com.wave.network.AbstractHandler;
import com.wave.network.AbstractMessage;
import com.wave.network.AbstractSerialize;
import com.wave.network.response.TaskSubmitResponse;
import com.wave.slave.AbstractOperator;
import com.wave.slave.execute.PriorityOperatorQueue;
import com.wave.slave.execute.PriorityOperatorRunner;

import java.io.IOException;

/**
 * @author liqiu.qlq
 */
public class TaskSubmitRequest extends AbstractMessage {
    private PlanNode planNode;

    public TaskSubmitRequest() {
    }

    public PlanNode getPlanNode() {
        return planNode;
    }

    public void setPlanNode(PlanNode planNode) {
        this.planNode = planNode;
    }

    @Override
    public AbstractHandler getHandle() {
        return new AbstractHandler<TaskSubmitRequest, TaskSubmitResponse>() {
            @Override
            public TaskSubmitResponse handle(TaskSubmitRequest param) {
                PlanNode planNode = param.getPlanNode();
                AbstractOperator operator = planNode.getFactory().offer(planNode);
                PriorityOperatorRunner runner = new PriorityOperatorRunner();
                runner.setOperator(operator);
                if (planNode.getPlanNode() != null) {
                    operator.setOperator(parseOperator(planNode.getPlanNode()));
                }
                PriorityOperatorQueue.get().add(runner);
                TaskSubmitResponse response = new TaskSubmitResponse();
                response.setOutParams("success");
                return response;
            }

            private AbstractOperator parseOperator(PlanNode planNode) {
                AbstractOperator operator = planNode.getFactory().offer(planNode);
                if (planNode.getPlanNode() == null) {
                    return operator;
                }
                operator.setOperator(parseOperator(planNode.getPlanNode()));
                return operator;
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
        return "planNode:" + JSON.toJSONString(planNode);
    }

}
