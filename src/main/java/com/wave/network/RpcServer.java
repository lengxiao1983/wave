package com.wave.network;

import com.wave.network.request.RequestType;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author shkstart
 * @create 2021-01-30 16:10
 */
@Slf4j
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
        log.info("server listening port:" + port);
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    //服务器
                    ServerSocket server = new ServerSocket(port);

                    while (true) {
                        Socket socket = server.accept();
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                        String name = (String) input.readObject();
                        RequestType type = RequestType.valueOf(name);
                        byte[] bytes = (byte[])input.readObject();
                        AbstractMessage msg = type.getMessage().getSerializer().deSerialize(bytes);
                        log.info("server received msg:" + msg.desc());
                        AbstractMessage response = msg.getHandle().handle(msg);

                        //返回给客户端即服务消费者数据
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        output.writeObject(msg.getRequestType().name());//消息头
                        output.writeObject(msg.getResponseSerializer().serialize(response));
                        log.info("service response msg:" + response.desc());
                    }
                } catch (Exception e) {
                    log.info(e.getMessage(), e);
                    throw new RuntimeException(e);
                }
            }
        };

        thread = new Thread(runnable);
        thread.start();

    }
}
