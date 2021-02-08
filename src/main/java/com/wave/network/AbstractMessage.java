package com.wave.network;

import com.wave.network.request.RequestType;

import java.io.Serializable;

/**
 * @author shkstart
 * @create 2021-01-30 15:16
 */
public abstract class AbstractMessage implements Serializable {

    public AbstractMessage() {

    }

    /**
     * 返回处理handle
     * @return
     */
    public abstract AbstractHandler getHandle();

    /**
     * 返回消息类型
     * @return
     */
    public abstract RequestType getRequestType();

    /**
     * 返回序列化对象
     * @return
     */
    public abstract AbstractSerialize getSerializer();

    /**
     * 返回response序列化对象
     * @return
     */
    public abstract AbstractSerialize getResponseSerializer();

    /**
     * 对象描述
     * @return
     */
    public String desc() {
        return this.getClass().getName();
    }
}
