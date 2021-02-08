package com.wave.cluster;

import com.wave.network.Address;
import lombok.Data;

/**
 * @author liqiu.qlq
 */
@Data
public abstract class ClusterNode {
    private Address address;

    /**
     * 获取节点类型
     * @return
     */
    public abstract ClusterType getClusterType();
}
