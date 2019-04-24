package com.kobold.context;

import java.sql.Connection;

public interface IDbFactory {
    Connection getConnection() ;
    String getDbName();
}
