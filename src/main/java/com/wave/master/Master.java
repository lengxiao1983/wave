package com.wave.master;

import com.wave.cluster.ClusterNode;
import com.wave.cluster.ClusterType;
import com.wave.network.Address;
import com.wave.slave.Slave;
import lombok.Data;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liqiu.qlq
 */
@Data
public class Master extends ClusterNode {

    /**
     * slave 节点
     */
    private static final ConcurrentHashMap<Address, Slave> slaves = new ConcurrentHashMap<Address, Slave>();

    @Override
    public ClusterType getClusterType() {
        return ClusterType.MASTER;
    }

}
