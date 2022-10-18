package connection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.StringTokenizer;

public class Connect {
	private static Connect instance;
	private static Connection connection;

	private Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String[] configs = readConfig();
			String url = "jdbc:mysql://" + configs[0] + (configs[1].length() == 0 ? "" : ":" + configs[1]) + "/"
					+ configs[2];
			String username = configs[3];
			String pass = configs[4];
			connection = DriverManager.getConnection(url, username, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private String[] readConfig() {
		String[] result = new String[5];
		File configFile = new File("configs/database_config.txt");
		if (configFile.exists()) {
			try {
				BufferedReader buff = new BufferedReader(new FileReader(configFile));
				String data;
				String host = "";
				String port = "";
				String databaseName = "";
				String userName = "";
				String password = "";
				while ((data = buff.readLine()) != null) {
					StringTokenizer stk = new StringTokenizer(data);
					String key = stk.nextToken().replaceAll(":", "");
					if (stk.countTokens() != 0) {
						switch (key) {
						case "HOST": {
							host = stk.nextToken();
							break;
						}
						case "PORT": {
							port = stk.nextToken();
							break;
						}
						case "DATABASE_NAME": {
							databaseName = stk.nextToken();
							break;
						}
						case "USER_NAME": {
							userName = stk.nextToken();
							break;
						}
						case "PASSWORD": {
							password = stk.nextToken();
							break;
						}
						default:
							throw new IllegalArgumentException("Unexpected value: " + key);

						}
					}
				}
				result[0] = host;
				result[1] = port;
				result[2] = databaseName;
				result[3] = userName;
				result[4] = password;
				buff.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
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
