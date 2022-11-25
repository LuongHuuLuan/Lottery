
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import configs.GetConfig;
import model.DatabaseConfig;

public class ConnectStaging {
	private static ConnectStaging instance;
	private static Connection connection;

	private ConnectStaging() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			DatabaseConfig databaseConfig = GetConfig.getDatabaseStagingConfigs();
			String url = "jdbc:mysql://" + databaseConfig.getHost() + ":" + databaseConfig.getPort() + "/"
					+ databaseConfig.getDatabaseName();
			String username = databaseConfig.getUserName();
			String pass = databaseConfig.getPassword();
			connection = DriverManager.getConnection(url, username, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static ConnectStaging getInstance() {
		if (instance == null)
			instance = new ConnectStaging();
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}
}
