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
        System.out.println("doGet");

        System.out.println(request.getParamter("aaa"));
        System.out.println(request.getParamter("bbb"));

        response.write("helloWorld.....");
    }


    @Override
    public void doPost(Request request, Response response) {
        System.out.println("doPost");
        System.out.println(request.getRequestBody());

        response.write("helloWorld.....");
    }

}
