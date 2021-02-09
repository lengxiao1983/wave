package com.wave.common.config;

import com.wave.cluster.ClusterType;
import com.wave.common.Constant;
import com.wave.network.Address;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.*;

/**
 * @author liqiu.qlq
 */
@Slf4j
public class Config {
    private final static Config instance = new Config();

    private Properties properties = new Properties();

    Config() {
    }

    public static Config get() {
        return instance;
    }

    public void init() throws Exception {
        InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties");
        properties.load(in);
        log.info("start load config...");
        for (Object key : properties.keySet()) {
            log.info(String.valueOf(key) + " : " + properties.getProperty(String.valueOf(key)));
        }
        log.info("config load success...");
    }

    public Address getMasterAddress() {
        String val = getString(Constant.CONFIG_KEY_MASTER_ADDRESS);
        if (val == null) {
            return null;
        }
        String[] arr = val.split(":");
        Address address = new Address();
        address.setIp(arr[0].trim());
        address.setPort(Integer.valueOf(arr[1]));
        return address;
    }

    public Integer getHttpListenPort() {
        return getInteger(Constant.CONFIG_KEY_HTTP_LISTEN_PORT, 8080);
    }

    public List<Address> getSlaveAddresses() {
        String val = getString(Constant.CONFIG_KEY_SLAVE_ADDRESS);
        if (val == null) {
            return null;
        }
        String[] array = val.split("\\|");
        List<Address> addressList = new ArrayList<Address>();
        for (String arr : array) {
            String[] ar = arr.split(":");
            Address address = new Address();
            address.setIp(ar[0].trim());
            address.setPort(Integer.valueOf(ar[1]));
            addressList.add(address);
        }
        return addressList;
    }

    public String getString(String key, String defaultValue) {
        String val = getString(key);
        return val != null ? val : defaultValue;
    }

    public Set<ClusterType> getClusterType() {
        String values = getString(Constant.CONFIG_KEY_CLUSTER_TYPE);
        if (values == null) {
            return null;
        }
        String[] arr = values.split("\\|");
        Set<ClusterType> clusterTypes = new HashSet<ClusterType>();
        for (String type : arr) {
            clusterTypes.add(ClusterType.valueOf(type.trim()));
        }
        return clusterTypes;
    }

    public Integer getListenPort() {
        return getInteger(Constant.CONFIG_KEY_LISTEN_PORT, 8099);
    }

    public String getString(String key) {
        return properties.getProperty(key);
    }

    public Integer getInteger(String key) {
        String val = getString(key);
        return val == null ? null : Integer.valueOf(val);
    }

    public Integer getInteger(String key, Integer defaultValue) {
        Integer val = getInteger(key);
        return val != null ? val : defaultValue;
    }

}
