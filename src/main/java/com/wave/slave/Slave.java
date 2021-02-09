package com.wave.slave;

import com.wave.cluster.ClusterNode;
import com.wave.cluster.ClusterType;
import com.wave.network.Address;
import com.wave.network.RpcServer;
import com.wave.slave.execute.OperatorExecute;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author liqiu.qlq
 */
@Data
@Slf4j
public class Slave extends ClusterNode {

    /**
     * 主节点
     */
    private Address masterAddress;

    /**
     * 最近心跳时间
     */
    private Date lastHBTime = new Date();

    public static final Slave instance = new Slave();

    public static Slave get() {
        return instance;
    }

    public Slave masterAddress(Address masterAddress) {
        this.masterAddress = masterAddress;
        return this;
    }

    @Override
    public ClusterType getClusterType() {
        return ClusterType.SLAVE;
    }

    public Slave start() throws Exception {
        log.info("start slave begin");
        OperatorExecute.get().start();
        new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        TimeUnit.SECONDS.sleep(300);
                        // todo 每隔三秒发送心跳
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();
        return this;
    }
}
