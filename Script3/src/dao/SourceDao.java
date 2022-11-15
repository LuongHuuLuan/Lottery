package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDW;
import connection.ConnectStaging;
import model.Source;

public class SourceDao {

	// add 1 row to the table source_dim in DataWH
	public static boolean addSourceToDaWH(String name, String url) {
		try {
			Connection connect = ConnectDW.getInstance().getConnection();
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

	// get 1 row in table source_dim in DataWH
	public static Source getSourceURLInDataWH(String url) {
		try {
			Connection connect = ConnectDW.getInstance().getConnection();
			String sql = "SELECT * from source_dim where url = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1, url);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				return new Source(resultset.getInt(1), resultset.getString(2), resultset.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new Source(0, "ERR", "ERR");
	}

	// get 1 row in table source_dim of Staging
	public static Source getSourceInStaging(int id) {
		try {
			Connection connect = ConnectStaging.getInstance().getConnection();
			String sql = "SELECT * from source_dim where id_sour = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				return new Source(resultset.getInt(1), resultset.getString(2), resultset.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
