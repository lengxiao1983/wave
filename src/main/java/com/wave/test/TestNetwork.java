package com.wave.test;

import com.wave.network.RpcServer;

/**
 * @author shkstart
 * @create 2021-01-30 15:48
 */
public class TestNetwork {

    public static void main(String[] args) throws Exception {
        testServer();
        System.exit(0);
    }


    private  static void testServer() throws Exception {
        RpcServer.get().start(8099);
    }

}
