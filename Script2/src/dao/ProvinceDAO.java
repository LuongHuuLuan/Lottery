package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectStaging;
import model.Province;

public class ProvinceDAO {
	public static boolean addProvince(String name) {
		try {
			Connection connect = ConnectStaging.getInstance().getConnection();
			String sql = "INSERT INTO province_dim(name) values(?)";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, name);
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

	public static Province getProvince(String name) {
		try {
			Connection connect = ConnectStaging.getInstance().getConnection();
			String sql = "SELECT id_pro, name from province_dim where name = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet resultset = ps.executeQuery();
			while (resultset.next()) {
				return new Province(resultset.getInt(1), resultset.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
