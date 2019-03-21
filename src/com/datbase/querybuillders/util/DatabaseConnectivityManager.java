package com.datbase.querybuillders.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.database.querybuilders.constants.DatabaseConstants;

public class DatabaseConnectivityManager {
	
private Connection connection;
	
	public DatabaseConnectivityManager(String datbaseURL, String userName, String password) throws ClassNotFoundException, SQLException{
		Class.forName(DatabaseConstants.MYSQL_JDBC_DRIVERNAME);
		this.connection = DriverManager.getConnection(datbaseURL, userName, password);
	}
	
	public Connection getConnection(){
		return this.connection;
	}
}
