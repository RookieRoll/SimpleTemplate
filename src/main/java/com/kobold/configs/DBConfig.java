package com.kobold.configs;

public class DBConfig {
	private String Url;
	private String UserName;
	private String Password;
	private String DriverName;
	private String DataBaseName;
	private String MaxPoolSize;

	public DBConfig(){
//		this.Url=url;
//		this.UserName=userName;
//		this.Password=password;
//		DriverName = driverName;
	}



	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getDataBaseName() {
		return DataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		DataBaseName = dataBaseName;
	}

	public String getDriverName() {
		return DriverName;
	}

	public void setDriverName(String driverName) {
		DriverName = driverName;
	}
}
