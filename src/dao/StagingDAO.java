package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.ConnectStaging;

public class StagingDAO {

	public static boolean loadCsvToStaging(String url) {
		try {
			Connection connect = ConnectStaging.getInstance().getConnection();
			String sql = "LOAD DATA INFILE ? INTO TABLE result_staging COLUMNS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"' ESCAPED BY '\"' LINES TERMINATED BY '\\n' IGNORE 1 LINES;";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, url);
			int rows = ps.executeUpdate();
			if (rows != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
