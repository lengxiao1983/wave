package com.wave.network;

import com.wave.network.response.AbstractResponse;

/**
 * @author shkstart
 * @create 2021-01-30 15:15
 */
public abstract class AbstractHandler<Req extends AbstractMessage, Rsp extends AbstractResponse> {

    /**
     * 消息处理
     * @param param
     * @return
     */
    public abstract Rsp handle(Req param);

}
