package com.kobold.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DefaultDbApplicationContext extends DbBaseApplicationContext {


	public DefaultDbApplicationContext() {
		super();
	}

	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
		try {
			this.dbConfig.setDataBaseName(dataSource.getConnection().getCatalog());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public DataSource getDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(dbConfig.getUrl());
		config.setUsername(dbConfig.getUserName());
		config.setPassword(dbConfig.getPassword());
		config.setDriverClassName(dbConfig.getDriverName());
		if(dbConfig.getMaxPoolSize().isPresent())
			config.setMaximumPoolSize(dbConfig.getMaxPoolSize().get());

		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}
}
