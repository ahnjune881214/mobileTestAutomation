package com.ahnlab.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	public static Connection getConnection() throws Exception {
		try {
			String url = "jdbc:mysql://localhost:3306/dev_mit&useUnicode=true&characterEncoding=utf8";
			String username = "root";
			String password = "password";
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(url, username, password);
			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
