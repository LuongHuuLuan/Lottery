
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import configs.GetConfig;
import model.DatabaseConfig;

public class ConnectControl {
	private static ConnectControl instance;
	private static Connection connection;

	private ConnectControl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			DatabaseConfig databaseConfig = GetConfig.getDatabaseControlConfigs();
			String url = "jdbc:mysql://" + databaseConfig.getHost() + ":" + databaseConfig.getPort() + "/"
					+ databaseConfig.getDatabaseName();
			String username = databaseConfig.getUserName();
			String pass = databaseConfig.getPassword();
			connection = DriverManager.getConnection(url, username, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static ConnectControl getInstance() {
		if (instance == null)
			instance = new ConnectControl();
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}
}
