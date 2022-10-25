package model;

public class DatabaseConfig {
	private String host;
	private int port;
	private String databaseName;
	private String userName;
	private String password;

	public DatabaseConfig() {
		port = 3306;
		password = "";
	}

	public DatabaseConfig(String host, int port, String databaseName, String userName, String password) {
		this.host = host;
		this.port = port;
		this.databaseName = databaseName;
		this.userName = userName;
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "DatabaseConfig [host=" + host + ", port=" + port + ", databaseName=" + databaseName + ", userName="
				+ userName + ", password=" + password + "]";
	}

}
