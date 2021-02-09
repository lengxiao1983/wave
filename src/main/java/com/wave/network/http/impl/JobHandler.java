package com.wave.network.http.impl;

import com.wave.network.http.HttpHandler;
import com.wave.network.http.Request;
import com.wave.network.http.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liqiu.qlq
 */
@Slf4j
public class JobHandler extends HttpHandler {

    @Override
    public void doGet(Request request, Response response) {
        log.info("JobHandler received msg:" + request.getReuestURI().toString());
        String exprString = request.getParamter("exprString");
        log.info("exprString:{}", exprString);

        response.write("helloWorld.....");
    }


    @Override
    public void doPost(Request request, Response response) {
        log.info("JobHandler received msg body:{}" + request.getRequestBody());

        response.write("helloWorld.....");
    }

}
