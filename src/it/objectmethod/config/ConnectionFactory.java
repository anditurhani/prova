package it.objectmethod.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private static final String URL = "jdbc:mysql://localhost/world";
	private static final String USER = "root";
	private static final String PASS = "root";

	public static Connection getConnection() {
		Connection conn = null;

		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

}
