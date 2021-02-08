package com.wave;

import com.wave.cluster.ClusterType;
import com.wave.expr.AbstractExpr;
import com.wave.expr.parse.ExprParseUtils;
import com.wave.master.Master;
import com.wave.master.plan.ExprPlanNode;
import com.wave.master.plan.InputPlanNode;
import com.wave.network.AbstractMessage;
import com.wave.network.Address;
import com.wave.network.RpcClient;
import com.wave.network.request.TaskSubmitRequest;
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
                Slave slave = new Slave();
                slave.setAddress(new Address().ip("").port(8099));
                Master.get().addSlave(slave);

                String exprString = "a + b + c > 20.0";
                AbstractExpr expr = ExprParseUtils.parse(exprString);
                TaskSubmitRequest request = new TaskSubmitRequest();
                InputPlanNode inputPlanNode = new InputPlanNode();
                ExprPlanNode exprPlanNode = new ExprPlanNode();
                exprPlanNode.setExpr(expr);
                exprPlanNode.setExprString(exprString);
                inputPlanNode.setPlanNode(exprPlanNode);
                request.setPlanNode(inputPlanNode);
                AbstractMessage response = RpcClient.send(Master.get().offerSlave(), request);


                break;
            case SLAVE:
                Slave.get().masterAddress(new Address().ip("").port(8099)).start(8099);
                break;
            default:
                throw new RuntimeException("cant support type:" + type.name());
        }
    }
}
