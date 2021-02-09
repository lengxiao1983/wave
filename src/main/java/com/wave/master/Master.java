package com.wave.master;

import com.wave.cluster.ClusterNode;
import com.wave.cluster.ClusterType;
import com.wave.network.Address;
import com.wave.network.RpcServer;
import com.wave.slave.Slave;
import lombok.Data;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author liqiu.qlq
 */
@Data
public class Master extends ClusterNode {

    private static Thread hbThread;

    Master() {
    }

    private static final Master instance = new Master();

    public static Master get() {
        return instance;
    }

    /**
     * slave 节点
     */
    private static final ConcurrentHashMap<Address, Slave> slaves = new ConcurrentHashMap<Address, Slave>();

    public Address offerSlave() {
        return slaves.keys().nextElement();
    }

    @Override
    public ClusterType getClusterType() {
        return ClusterType.MASTER;
    }


    public Master start() throws Exception {
        hbThread = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        TimeUnit.SECONDS.sleep(3);
                        for (Map.Entry<Address, Slave> entry : slaves.entrySet()) {
                            Slave slave = entry.getValue();
                            if (System.currentTimeMillis() - slave.getLastHBTime().getTime() > Integer.MAX_VALUE * 1000) {
                                slaves.remove(slave.getAddress());
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    if (hbThread != null) {
                        hbThread.start();
                    }
                }
            }
        });
        hbThread.start();
        return this;
    }


    public void addSlave(Slave slave) {
        slaves.put(slave.getAddress(), slave);
    }

    public Slave removeSlave(Address address) {
        return slaves.remove(address);
    }

    public void heartbeat(Slave slave) {
        Slave existSlave = removeSlave(slave.getAddress());
        existSlave.setLastHBTime(new Date());
    }

}
