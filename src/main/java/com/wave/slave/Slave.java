package com.wave.slave;

import com.wave.cluster.ClusterNode;
import com.wave.cluster.ClusterType;
import com.wave.master.Master;

/**
 * @author liqiu.qlq
 */
public class Slave extends ClusterNode {

    /**
     * 主节点
     */
    private Master master;

    @Override
    public ClusterType getClusterType() {
        return ClusterType.SLAVE;
    }
}