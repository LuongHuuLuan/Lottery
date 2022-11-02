package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import configs.GetConfig;
import model.DatabaseConfig;

public class Connect {
	private static Connect instance;
	private static Connection connection;

	private Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			DatabaseConfig databaseConfig = GetConfig.getDatabaseConfigs();
			String url = "jdbc:mysql://" + databaseConfig.getHost() + ":" + databaseConfig.getPort() + "/"
					+ databaseConfig.getDatabaseName();
			String username = databaseConfig.getUserName();
			String pass = databaseConfig.getPassword();
			connection = DriverManager.getConnection(url, username, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connect getInstance() {
		if (instance == null)
			instance = new Connect();
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}
}
