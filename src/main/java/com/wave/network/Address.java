package com.wave.network;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shkstart
 * @create 2021-01-30 16:06
 */
@Data
public class Address implements Serializable {
    private String ip;
    private int port;

    public Address ip(String ip) {
        this.ip = ip;
        return this;
    }

    public Address port(int port) {
        this.port = port;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return port == address.port &&
                ip.equals(address.ip);
    }

    @Override
    public int hashCode() {
        return port;
    }
}
