package com.wave;

import com.wave.cluster.ClusterType;
import com.wave.common.config.Config;
import com.wave.expr.AbstractExpr;
import com.wave.expr.parse.ExprParseUtils;
import com.wave.master.Master;
import com.wave.master.plan.ExprPlanNode;
import com.wave.master.plan.InputPlanNode;
import com.wave.network.AbstractMessage;
import com.wave.network.Address;
import com.wave.network.RpcClient;
import com.wave.network.RpcServer;
import com.wave.network.request.TaskSubmitRequest;
import com.wave.slave.Slave;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author liqiu.qlq
 */
@Data
@Slf4j
public class AppMain {
    private static ClusterType type = ClusterType.MASTER;

    public static void main(String[] args) throws Exception {
        // 初始化配置文件
        Config.get().init();

        // 读取监听端口
        Integer listenPort = Config.get().getListenPort();

        // 启动服务
        RpcServer.get().start(listenPort);

        // 读取本地节点集群类型
        Set<ClusterType> clusterTypeSet = Config.get().getClusterType();

        // 启动节点
        for (ClusterType type : clusterTypeSet) {
            switch (type) {
                case MASTER:
                    Master.get().start();
                    break;
                case SLAVE:
                    Slave.get().start();
                    break;
                default:
                    throw new RuntimeException();
            }
        }
    }
}
