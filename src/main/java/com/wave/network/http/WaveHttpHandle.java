package com.wave.network.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

/**
 * @author liqiu.qlq
 */
public class WaveHttpHandle implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        HttpRequest request = new HttpRequest(httpExchange);
        HttpResponse response = new HttpResponse(httpExchange);
        Handler handler = Context.getHandler(request.getReuestURI().getPath());
        handler.service(request, response);

    }

}
