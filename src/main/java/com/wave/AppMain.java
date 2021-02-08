package com.wave;

import com.wave.cluster.ClusterType;
import com.wave.master.Master;
import com.wave.network.Address;
import com.wave.slave.Slave;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liqiu.qlq
 */
@Data
public class AppMain {
    private static ClusterType type = ClusterType.MASTER;

    private Map<String, String> readConfig() {
        return new HashMap<String, String>();
    }

    private ClusterType getClusterType() {
        Map<String, String> config = readConfig();
        return type;
    }

    public static void main(String[] args) throws Exception {
        switch (type) {
            case MASTER:
                Master.get().start(8099);
                break;
            case SLAVE:
                Slave.get().masterAddress(new Address().ip("").port(8099)).start(8099);
                break;
            default:
                throw new RuntimeException("cant support type:" + type.name());
        }
    }
}
