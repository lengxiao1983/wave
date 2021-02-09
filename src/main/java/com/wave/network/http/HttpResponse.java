package com.wave.network.http;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;


public class HttpResponse implements Response {

    private HttpExchange httpExchange;
    public HttpResponse(HttpExchange httpExchange){
        this.httpExchange = httpExchange;
    }

    public void write(String result) {
        try {
            byte[] bytes = result.getBytes();
            // 设置响应头属性及响应信息的长度
            httpExchange.sendResponseHeaders(200, bytes.length);
            httpExchange.getResponseHeaders().set("Content-Type", "application/json;charset=utf-8");
            // 获得输出流
            OutputStream out = httpExchange.getResponseBody();
            out.write(bytes);
            out.flush();
            httpExchange.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
