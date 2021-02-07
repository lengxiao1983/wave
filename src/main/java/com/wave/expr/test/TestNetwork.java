package com.wave.expr.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wave.expr.master.TasksubmitRequest;
import com.wave.expr.network.AbstractMessage;
import com.wave.expr.network.Address;
import com.wave.expr.network.RpcClient;
import com.wave.expr.network.RpcServer;

import javax.sound.midi.SoundbankResource;
import java.util.concurrent.TimeUnit;

/**
 * @author shkstart
 * @create 2021-01-30 15:48
 */
public class TestNetwork {

    public static void main(String[] args) throws Exception {
        testServer();

        for (int i = 0; i<10; i++) {
            TimeUnit.SECONDS.sleep(2);
            AbstractMessage response = RpcClient.send(new Address("", 8099), new TasksubmitRequest().inParams("hello world!"));
            System.out.println("return response:" + JSON.toJSONString(response));

            TasksubmitRequest request = new TasksubmitRequest();
            request.setInParams("hello world!");
            String params = JSON.toJSONString(request, SerializerFeature.WriteClassName);
            System.out.println();

        }

        System.exit(0);
    }

    private static void testClient() throws Exception {
        AbstractMessage response = RpcClient.send(new Address("", 8099), new TasksubmitRequest().inParams("hello world!"));
        System.out.println("return response:" + JSON.toJSONString(response));
    }

    private  static void testServer() throws Exception {
        RpcServer.get().start(8099);

    }


}
