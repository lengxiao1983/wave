package com.wave.expr.network.response;

import com.wave.expr.network.AbstractHandler;
import com.wave.expr.network.AbstractMessage;
import com.wave.expr.network.AbstractSerialize;
import com.wave.expr.network.request.RequestType;

/**
 * @author liqiu.qlq
 */
public abstract class AbstractResponse extends AbstractMessage {

    /**
     * 返回序列化对象
     * @return
     */
    @Override
    public AbstractSerialize getSerializer() {
        return null;
    }

    @Override
    public AbstractHandler getHandle() {
        return null;
    }

    /**
     * 返回response序列化对象
     * @return
     */
    @Override
    public AbstractSerialize getResponseSerializer() {
        return null;
    }

    @Override
    public RequestType getRequestType() {
        return null;
    }
}
