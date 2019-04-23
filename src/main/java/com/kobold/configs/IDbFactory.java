package com.kobold.configs;

import java.sql.Connection;

public interface IDbFactory {
    Connection getConnection() ;
    String getDbName();
}
