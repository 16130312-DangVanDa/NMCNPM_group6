package model.account;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class GetConnection {

	public Connection getConnection() {
		String driver = null;
		String url = null;
		String user = null;
		String pass = null;
		try (InputStream input = new FileInputStream("properties/config.properties")) {
			Properties prop = new Properties();
			// load a properties file
			prop.load(input);
			// doc tu file property
			driver = prop.getProperty("driver");
			System.out.println(driver);
			url = prop.getProperty("url");
			System.out.println(url);
			user = prop.getProperty("user");
			System.out.println(user);
			pass = "    ";

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
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
