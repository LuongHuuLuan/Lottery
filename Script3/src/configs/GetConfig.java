package configs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import model.DatabaseConfig;

public class GetConfig {
	// read all content configs file
	public static String readConfigsFile() {
		String fileContent = "";
		String currentPath = Paths.get("").toAbsolutePath().toString();
		File configFile = new File(currentPath + "\\dwConfigs.txt");

		if (configFile.exists()) {
			try {
				BufferedReader buff = new BufferedReader(new FileReader(configFile));
				String line = "";
				while ((line = buff.readLine()) != null) {
					fileContent += line;
				}
				buff.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "File not exist or can't read file");
			}
		}
		return fileContent.trim();
	}

	public static DatabaseConfig getDatabaseControlConfigs() {
		DatabaseConfig result = null;
		String fileContent = readConfigsFile();
		String[] databaseConfigs = fileContent.split("}");
		for (String databaseConfig : databaseConfigs) {
			databaseConfig = databaseConfig.trim();
			if (databaseConfig.substring(0, databaseConfig.indexOf("{") + 1).equalsIgnoreCase("DATABASE_CONTROL {")) {
				databaseConfig = databaseConfig.substring(databaseConfig.indexOf("DATABASE_CONTROL {") + 19);
				if (databaseConfig.length() != 0) {
					result = new DatabaseConfig();
					String[] props = databaseConfig.split(",");
					for (String prop : props) {
						prop = prop.trim();
						String[] keyAndValue = prop.split(":");
						switch (keyAndValue[0].trim()) {
						case "HOST": {
							result.setHost(keyAndValue[1].trim());
							break;
						}
						case "PORT": {
							result.setPort(Integer.parseInt(keyAndValue[1].trim()));
							break;
						}
						case "DATABASE_NAME": {
							result.setDatabaseName(keyAndValue[1].trim());
							;
							break;
						}
						case "USER_NAME": {
							result.setUserName(keyAndValue[1].trim());
							;
							break;
						}
						case "PASSWORD": {
							if (keyAndValue.length == 2) {
								result.setPassword(keyAndValue[1].trim());
							}
							break;
						}
						default:
							throw new IllegalArgumentException("Not found key");
						}
					}
				}
			}
		}
		return result;
	}

	public static DatabaseConfig getDatabaseStagingConfigs() {
		DatabaseConfig result = null;
		String fileContent = readConfigsFile();
		String[] databaseConfigs = fileContent.split("}");
		for (String databaseConfig : databaseConfigs) {
			databaseConfig = databaseConfig.trim();
			String configName = databaseConfig.substring(0, databaseConfig.indexOf("{"));
			if (configName.trim().equals("DATABASE_STAGING")) {
				if (databaseConfig.substring(0, databaseConfig.indexOf("{") + 1)
						.equalsIgnoreCase("DATABASE_STAGING {")) {
					databaseConfig = databaseConfig.substring(databaseConfig.indexOf("DATABASE_STAGING {") + 19);
					if (databaseConfig.length() != 0) {
						result = new DatabaseConfig();
						String[] props = databaseConfig.split(",");
						for (String prop : props) {
							prop = prop.trim();
							String[] keyAndValue = prop.split(":");
							switch (keyAndValue[0].trim()) {
							case "HOST": {
								result.setHost(keyAndValue[1].trim());
								break;
							}
							case "PORT": {
								result.setPort(Integer.parseInt(keyAndValue[1].trim()));
								break;
							}
							case "DATABASE_NAME": {
								result.setDatabaseName(keyAndValue[1].trim());
								;
								break;
							}
							case "USER_NAME": {
								result.setUserName(keyAndValue[1].trim());
								;
								break;
							}
							case "PASSWORD": {
								if (keyAndValue.length == 2) {
									result.setPassword(keyAndValue[1].trim());
								}
								break;
							}
							default:
								throw new IllegalArgumentException("Not found key");
							}
						}
					}
				}
			}
		}

		return result;
	}

	public static DatabaseConfig getDatabaseDataWarehouseConfigs() {
		DatabaseConfig result = null;
		String fileContent = readConfigsFile();
		String[] databaseConfigs = fileContent.split("}");
		for (String databaseConfig : databaseConfigs) {
			databaseConfig = databaseConfig.trim();
			String configName = databaseConfig.substring(0, databaseConfig.indexOf("{"));
			if (configName.trim().equals("DATABASE_DATA_WAREHOUSE")) {
				if (databaseConfig.substring(0, databaseConfig.indexOf("{") + 1)
						.equalsIgnoreCase("DATABASE_DATA_WAREHOUSE {")) {
					databaseConfig = databaseConfig.substring(databaseConfig.indexOf("DATABASE_DATA_WAREHOUSE {") + 26)
							.trim();
					if (databaseConfig.length() != 0) {
						result = new DatabaseConfig();
						String[] props = databaseConfig.split(",");
						for (String prop : props) {
							prop = prop.trim();
							String[] keyAndValue = prop.split(":");
							switch (keyAndValue[0].trim()) {
							case "HOST": {
								result.setHost(keyAndValue[1].trim());
								break;
							}
							case "PORT": {
								result.setPort(Integer.parseInt(keyAndValue[1].trim()));
								break;
							}
							case "DATABASE_NAME": {
								result.setDatabaseName(keyAndValue[1].trim());
								;
								break;
							}
							case "USER_NAME": {
								result.setUserName(keyAndValue[1].trim());
								;
								break;
							}
							case "PASSWORD": {
								if (keyAndValue.length == 2) {
									result.setPassword(keyAndValue[1].trim());
								}
								break;
							}
							default:
								throw new IllegalArgumentException("Not found key");
							}
						}
					}
				}
			}
		}

		return result;
	}
}
