
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import configs.GetConfig;
import configs.GetConfigStaging;
import model.DatabaseConfig;

public class ConnectDBStaging {
	private static ConnectDBStaging instance;
	private static Connection connection;

	private ConnectDBStaging() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			DatabaseConfig databaseConfig = GetConfigStaging.getDatabaseConfigs();
			String url = "jdbc:mysql://" + databaseConfig.getHost() + ":" + databaseConfig.getPort() + "/"
					+ databaseConfig.getDatabaseName();
			String username = databaseConfig.getUserName();
			String pass = databaseConfig.getPassword();
			connection = DriverManager.getConnection(url, username, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static ConnectDBStaging getInstance() {
		if (instance == null)
			instance = new ConnectDBStaging();
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}
}
