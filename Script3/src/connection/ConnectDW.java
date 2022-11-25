package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import configs.GetConfig;
import model.DatabaseConfig;

public class ConnectDW {
	private static ConnectDW instance;
	private static Connection connection;

	private ConnectDW() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			DatabaseConfig databaseConfig = GetConfig.getDatabaseDataWarehouseConfigs();
			String url = "jdbc:mysql://" + databaseConfig.getHost() + ":" + databaseConfig.getPort() + "/"
					+ databaseConfig.getDatabaseName();
			String username = databaseConfig.getUserName();
			String pass = databaseConfig.getPassword();
			connection = DriverManager.getConnection(url, username, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static ConnectDW getInstance() {
		if (instance == null)
			instance = new ConnectDW();
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}
}