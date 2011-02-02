package com.ninj.ninjsupport;

import java.sql.Connection;

import com.ninj.helpers.DBHelper;

public class DBConfig {

	String url;
	
	String username;
	
	String password;
	
	String dataSource;

	public DBConfig(String dataSource) throws Exception {
		this.dataSource = dataSource;
	}

	public DBConfig(String url, String username, String password)
			throws Exception {

		this.url = url;
		this.username = username;
		this.password = password;
	}

	public Connection getConnection() throws Exception{
		 if (this.dataSource != null) {
			return DBHelper.getConnection(this.dataSource);
		} else {
			return DBHelper.getConnection(this.url, this.username,this.password);
		}
	}

}
