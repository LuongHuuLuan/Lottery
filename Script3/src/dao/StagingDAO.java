package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connection.ConnectStaging;

public class StagingDAO {

	// read all content configs file
	public static ArrayList<String> readTruncateStaging() {
		ArrayList<String> result = new ArrayList<String>();
		String currentPath = Paths.get("").toAbsolutePath().toString();
		File truncateFile = new File(currentPath + "\\truncateStaging.txt");

		if (truncateFile.exists()) {
			try {
				BufferedReader buff = new BufferedReader(new FileReader(truncateFile));
				String line = "";
				while ((line = buff.readLine()) != null) {
					result.add(line);
				}
				buff.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "File not exist or can't read file");
			}
		}
		return result;
	}

//truncate Database Staging
	public static void deleteDateStaging() {
		try {
			Connection connect = ConnectStaging.getInstance().getConnection();
			ArrayList<String> sqls = readTruncateStaging();
			PreparedStatement preparedStatement = connect.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
			preparedStatement.execute();
			preparedStatement.close();
			for (String s : sqls) {
				preparedStatement = connect.prepareStatement(s);
				preparedStatement.executeUpdate();
				preparedStatement.close();
			}
			preparedStatement = connect.prepareStatement("SET FOREIGN_KEY_CHECKS = 1");
			preparedStatement.execute();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}