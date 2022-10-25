package configs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import model.DatabaseConfig;

public class getConfig {
	// read all content configs file
	public static String readConfigsFile() {
		String fileContent = "";
		String currentPath = Paths.get("").toAbsolutePath().toString();
		File configFile = new File(currentPath + "\\configs.txt");

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

	public static DatabaseConfig getDatabaseConfigs() {
		DatabaseConfig result = null;
		String fileContent = readConfigsFile();
		String[] databaseConfigs = fileContent.split("}");
		for (String databaseConfig : databaseConfigs) {
			databaseConfig = databaseConfig.trim();
			if (databaseConfig.substring(0, databaseConfig.indexOf("{") + 1).equalsIgnoreCase("DATABASE {")) {
				databaseConfig = databaseConfig.substring(databaseConfig.indexOf("DATABASE {") + 10);
				if (databaseConfig.length() != 0) {
					result = new DatabaseConfig();
					String[] props = databaseConfig.split(",");
					for (String prop : props) {
						prop = prop.trim();
						String[] keyAndValue = prop.split(":");
						if (keyAndValue.length == 2) {
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
								result.setPassword(keyAndValue[1].trim());
								break;
							}
							default:
								throw new IllegalArgumentException("Not found key");
							}
						}
					}
				}
				break;
			}
		}
		return result;
	}
}
