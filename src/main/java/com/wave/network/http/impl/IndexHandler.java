package com.wave.network.http.impl;

import com.wave.network.http.HttpHandler;
import com.wave.network.http.Request;
import com.wave.network.http.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * @author liqiu.qlq
 */
@Slf4j
public class IndexHandler extends HttpHandler  {
    private static String indexHtml;

    static {
        InputStream in = null;
        try {
            in = IndexHandler.class.getClassLoader().getResourceAsStream("html/index.html");
            int len = in.available();
            byte[] bytes = new byte[len];
            in.read(bytes);
            indexHtml = new String(bytes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void doGet(Request request, Response response) {
        log.info("IndexHandler received msg:" + request.getReuestURI().toString());

        response.write(indexHtml);
    }


    @Override
    public void doPost(Request request, Response response) {
        log.info("IndexHandler received msg body:{}" + request.getRequestBody());

        response.write(indexHtml);
    }
}
