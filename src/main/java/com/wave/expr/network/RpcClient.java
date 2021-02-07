package com.wave.expr.network;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wave.expr.master.TasksubmitRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author shkstart
 * @create 2021-01-30 16:02
 */
public class RpcClient {
    public static AbstractMessage send(Address address, AbstractMessage params) throws Exception {
        Socket consumer = null;
        try {
            //需要传递的参数
            consumer = new Socket(address.getIp(), address.getPort());

            //将方法名称和参数 传递给服务生产者
            ObjectOutputStream output = new ObjectOutputStream(consumer.getOutputStream());
            String msg = JSON.toJSONString(params);
            output.writeObject(msg);
            System.out.println("client send Msg:" + msg);
            ObjectInputStream input = new ObjectInputStream(consumer.getInputStream());
            String jsonString = String.valueOf(input.readObject());

            TasksubmitRequest.TasksubmitResponse response = JSON.parseObject(jsonString, TasksubmitRequest.TasksubmitResponse.class);
            System.out.println("client received response:" + response);
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
