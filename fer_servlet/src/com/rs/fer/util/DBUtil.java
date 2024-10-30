package com.rs.fer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	public static Connection getConnection() {

		Connection connection = null;

		try {

			// 1. Register the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Get the connection object
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fer_jdbc?useSSL=false", "root",
					"root");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();

		} catch (SQLException se) {
			se.printStackTrace();
		}

		return connection;

	}

	public static void closeConnection(Connection connection) {

		try {

			// 5. Close the connection object
			connection.close();

		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

}
