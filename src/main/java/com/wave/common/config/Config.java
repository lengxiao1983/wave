package com.wave.common.config;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Properties;

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
        Properties properties = new Properties();
        InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties");
        properties.load(in);
        System.out.println("config:" + properties.toString());
        System.out.println("config load success...");

    }

    public String getString(String key, String defaultValue) {
        String val = getString(key);
        return val != null ? val : defaultValue;
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

    public static void main(String[] args) throws Exception {
        Config.get().init();
        log.warn("abc");
    }
}
