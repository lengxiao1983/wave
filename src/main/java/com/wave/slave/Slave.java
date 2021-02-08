package com.wave.slave;

import com.wave.cluster.ClusterNode;
import com.wave.cluster.ClusterType;
import com.wave.network.Address;

/**
 * @author liqiu.qlq
 */
public class Slave extends ClusterNode {

    /**
     * 主节点
     */
    private Address masterAddress;

    @Override
    public ClusterType getClusterType() {
        return ClusterType.SLAVE;
    }
}
