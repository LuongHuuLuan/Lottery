package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectStaging;
import model.Source;

public class SourceDao {
	public static Source getSource(String name, String url) {
		try {
			Connection connect = ConnectStaging.getInstance().getConnection();
			String sql = "SELECT id_sour, name, url from source_dim where name = ? and url = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, url);
			ResultSet resultset = ps.executeQuery();
			while (resultset.next()) {
				return new Source(resultset.getInt(1), resultset.getString(2), resultset.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean addSource(String name, String url) {
		try {
			Connection connect = ConnectStaging.getInstance().getConnection();
			String sql = "INSERT INTO source_dim(name, url) values(?,?)";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, url);
			int status = ps.executeUpdate();
			if (status > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
