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

		dataSource = getDataSource();

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
		try {
			if(null==dbConfig.getDataBaseName()||"".equals(dbConfig.getDataBaseName()))
				dbConfig.setDataBaseName(dataSource.getConnection().getCatalog());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return dbConfig.getDataBaseName();
	}
}
