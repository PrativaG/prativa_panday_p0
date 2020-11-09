package com.prativa_panday_p0p2.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private Connection conn;
	
	public Connection createConnection() throws SQLException {
		
	Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?", "postgres", "Panday90");
		
	return conn;
	
	}
}
