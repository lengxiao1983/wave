package com.wave.expr.network;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wave.expr.master.TasksubmitRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shkstart
 * @create 2021-01-30 16:10
 */
public class RpcServer {
    private final static RpcServer instance = new RpcServer();
    private  Thread thread ;
    private boolean hasStart = false;

    public static RpcServer get() {
        return instance;
    }

    RpcServer(){ }

    public synchronized void start(final int port) throws Exception {
        if (hasStart) {
            return;
        }
        hasStart = true;
        System.out.println("server listening");
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    //服务器
                    ServerSocket server = new ServerSocket(port);

                    while (true) {
                        Socket socket = server.accept();
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                        String jsonString = String.valueOf(input.readObject());
                        System.out.println("server received msg:" + jsonString);
                        ParserConfig config = new ParserConfig();
                        config.setAutoTypeSupport(true);
                        AbstractMessage request =  JSON.parseObject(jsonString, TasksubmitRequest.class);
                        AbstractMessage response = request.getHandle().handle(request);

                        //返回给客户端即服务消费者数据
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        String msg = JSON.toJSONString(response);
                        output.writeObject(msg);
                        System.out.println("service response msg:" + msg);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println(e.getCause());
                    throw new RuntimeException(e);
                }
            }
        };

        thread = new Thread(runnable);
        thread.start();

    }
}
