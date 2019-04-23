package com.kobold.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DefaultDbApplicationContext extends DbBaseApplicationContext {


	public DefaultDbApplicationContext(DBConfig dbConfig) {
		super(dbConfig);
	}

//	public void setDataSource(DataSource dataSource){
//		this.dataSource=dataSource;
//	}

	@Override
	public DataSource getDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(dbConfig.getUrl());
		config.setUsername(dbConfig.getUserName());
		config.setPassword(dbConfig.getPassword());
		config.setDriverClassName(dbConfig.getDriverName());

		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}
}
