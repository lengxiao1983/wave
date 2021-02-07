package com.wave.expr.network;

import com.alibaba.fastjson.JSON;
import com.wave.expr.network.request.RequestType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author shkstart
 * @create 2021-01-30 16:02
 */
public class RpcClient {
    public static AbstractMessage send(Address address, AbstractMessage params) throws Exception {
        Socket consumer = null;
        try {
            // 需要传递的参数
            consumer = new Socket(address.getIp(), address.getPort());

            // 将方法名称和参数 传递给服务生产者
            ObjectOutputStream output = new ObjectOutputStream(consumer.getOutputStream());
            byte[] bytes = params.getSerializer().serialize(params);

            // 写消息头
            output.writeObject(params.getRequestType().name());
            // 写消息体
            output.writeObject(bytes);
            System.out.println("client send Msg:" + params.desc());
            ObjectInputStream input = new ObjectInputStream(consumer.getInputStream());

            // 读消息头
            String name = (String) input.readObject();
            // 读消息体
            RequestType type = RequestType.valueOf(name);
            byte[] responseBytes = (byte[]) input.readObject();
            AbstractMessage response = type.getMessage().getResponseSerializer().deSerialize(responseBytes);
            System.out.println("client received response:" + response.desc());
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            throw new RuntimeException(e);
        } finally {
            if (consumer != null) {
                consumer.close();
            }
        }
    }
}
