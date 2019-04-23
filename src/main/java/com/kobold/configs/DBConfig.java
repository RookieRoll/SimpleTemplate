package com.kobold.configs;

import com.kobold.qkutils.PropertiesUtil;

import java.util.Optional;

public class DBConfig {
    private String url;
    private String userName;
    private String password;
    private String driverName;
    private String dataBaseName;
    private Optional<Integer> maxPoolSize;
    private static final String propertiesFilePath = "properties/db.properties";

    public DBConfig() {}

    public static DBConfig getInstance() {
        return PropertiesUtil.loadProperty(propertiesFilePath,DBConfig.class);
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Optional<Integer> getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Optional<Integer> maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }
}
