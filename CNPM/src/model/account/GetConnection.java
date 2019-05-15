package model.account;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//lay copnnection
public class GetConnection {

	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://103.98.148.33:3306/chickennlu_demo";
	String user = "chickennlu_dangvanda";
	String pass = "khongmatkhau";

	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			try {
				conn = DriverManager.getConnection(url, user, pass);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;

	}

	public static void main(String[] args) {
		Connection conn = new GetConnection().getConnection();
		if (conn != null) {
			System.out.println("ok");
		} else {
			System.out.println("not");
		}
	}
}
