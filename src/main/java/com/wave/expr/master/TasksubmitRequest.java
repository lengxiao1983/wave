package com.wave.expr.master;

import com.wave.expr.network.AbstractHandler;
import com.wave.expr.network.AbstractMessage;
import javafx.concurrent.Task;

/**
 * @author shkstart
 * @create 2021-01-30 15:38
 */
public class TasksubmitRequest extends AbstractMessage {
    private String inParams;

    public TasksubmitRequest() {
        super();
    }

    public TasksubmitRequest(String inParams) {
        super();
        this.inParams = inParams;
    }

    public String getInParams() {
        return inParams;
    }

    public void setInParams(String inParams) {
        this.inParams = inParams;
    }

    public TasksubmitRequest inParams(String inParams) {
        this.inParams = inParams;
        return this;
    }

    @Override
    public String toString() {
        return "inParams:" + inParams;
    }

    public static class TasksubmitResponse extends AbstractMessage {
        public TasksubmitResponse() {
            super();
        }

        private String outParms;

        public String getOutParms() {
            return outParms;
        }

        public void setOutParms(String outParms) {
            this.outParms = outParms;
        }

        @Override
        public String toString() {
            return "outParams:" + outParms;
        }
    }

    public AbstractHandler getHandle() {
        return new AbstractHandler() {
            @Override
            public AbstractMessage handle(AbstractMessage param) {
                if (! (param instanceof TasksubmitRequest)) {
                    throw new RuntimeException("param "+ param.getClass().getName() +" class not match");
                }
                System.out.println("received msg:" + inParams);
                TasksubmitResponse response = new TasksubmitResponse();
                response.setOutParms("outParams");
                return response;
            }
        };
    }
}
