package com.kobold.configs;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DbBaseApplicationContext {
	protected DBConfig dbConfig;
	protected DataSource dataSource;

	public DbBaseApplicationContext() {
		dbConfig = DBConfig.getInstance();
		try {
			dataSource=getDataSource();
			dbConfig.setDataBaseName(dataSource.getConnection().getCatalog());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void InitConfig(){

	}
	public abstract DataSource getDataSource();


	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public String getDbName() {
		return dbConfig.getDataBaseName();
	}
}
