package com.wave.network.http;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liqiu.qlq
 */
@Slf4j
public class WaveHttpServer {

    public static void start(int port) throws IOException {
        Context.load();

        HttpServerProvider provider = HttpServerProvider.provider();
        //监听端口,能同时接受10个并发请求
        HttpServer httpserver =provider.createHttpServer(new InetSocketAddress(port), 10);
        httpserver.createContext(Context.contextPath, new WaveHttpHandle());
        httpserver.setExecutor(null);
        httpserver.start();
        log.info("http server started...");
    }

}
