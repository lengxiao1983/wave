package com.wave.network.http.impl;

import com.alibaba.fastjson.JSON;
import com.wave.expr.AbstractExpr;
import com.wave.expr.parse.ExprParseUtils;
import com.wave.master.Master;
import com.wave.master.plan.ExprPlanNode;
import com.wave.master.plan.InputPlanNode;
import com.wave.network.AbstractMessage;
import com.wave.network.Address;
import com.wave.network.RpcClient;
import com.wave.network.http.HttpHandler;
import com.wave.network.http.Request;
import com.wave.network.http.Response;
import com.wave.network.request.TaskSubmitRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liqiu.qlq
 */
@Slf4j
public class JobHandler extends HttpHandler {

    @Override
    public void doGet(Request request, Response response) {
        try {
            log.info("JobHandler received msg:" + request.getReuestURI().toString());
            String exprString = request.getParamter("exprString");
            log.info("exprString:{}", exprString);
            AbstractExpr expr = ExprParseUtils.parse(exprString);
            InputPlanNode inputPlanNode = new InputPlanNode();
            ExprPlanNode exprPlanNode = new ExprPlanNode();
            exprPlanNode.setExprString(exprString);
            exprPlanNode.setExpr(expr);
            inputPlanNode.setPlanNode(exprPlanNode);
            TaskSubmitRequest taskSubmitRequest = new TaskSubmitRequest();
            taskSubmitRequest.setPlanNode(inputPlanNode);
            Address address = Master.get().offerSlaveAddress();
            AbstractMessage taskSubmitResponse = RpcClient.send(address, taskSubmitRequest);
            log.info("job submit success, response:{}", JSON.toJSONString(taskSubmitResponse));

            response.write("job submit success, response:" + JSON.toJSONString(taskSubmitResponse));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.write(e.getMessage());
        }
    }


    @Override
    public void doPost(Request request, Response response) {
        log.info("JobHandler received msg body:{}" + request.getRequestBody());

        response.write("helloWorld.....");
    }

}
