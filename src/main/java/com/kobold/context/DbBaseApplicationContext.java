package com.kobold.context;

import com.kobold.configs.DBConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DbBaseApplicationContext implements IDbFactory {
    protected DBConfig dbConfig;
    protected DataSource dataSource;

    public DbBaseApplicationContext() {
        dbConfig = DBConfig.getInstance();
        try {
            dataSource = getDataSource();
            dbConfig.setDataBaseName(dataSource.getConnection().getCatalog());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public abstract DataSource getDataSource();


    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDbName() {
        return dbConfig.getDataBaseName();
    }
}
