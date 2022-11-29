package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connect;
import model.Config;

public class ConfigDAO {
	public static Config getConfig(int id) {
		Config result = null;
		try {
			Connection connect = Connect.getInstance().getConnection();
			String sql = "select * from file_configuration where id =?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = new Config();
				result.setId(rs.getInt("id"));
				result.setSourceCode(rs.getString("source_code"));
				result.setSourceName(rs.getString("source_name"));
				result.setSourceUrl(rs.getString("source_url"));
				result.setLocalStogrePath(rs.getString("local_stogre_path"));
				result.setFtp(rs.getString("ftp"));
				result.setUserName(rs.getString("user"));
				result.setPassword(rs.getString("pass"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static List<Config> getConfig() {
		List<Config> result = new ArrayList<Config>();
		try {
			Connection connect = Connect.getInstance().getConnection();
			String sql = "select * from file_configuration";
			PreparedStatement ps = connect.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Config cf = new Config();
				cf.setId(rs.getInt("id"));
				cf.setSourceCode(rs.getString("source_code"));
				cf.setSourceName(rs.getString("source_name"));
				cf.setSourceUrl(rs.getString("source_url"));
				cf.setLocalStogrePath(rs.getString("local_stogre_path"));
				cf.setFtp(rs.getString("ftp"));
				cf.setUserName(rs.getString("user"));
				cf.setPassword(rs.getString("pass"));
				result.add(cf);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
