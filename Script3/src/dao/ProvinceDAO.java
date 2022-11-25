package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectDW;
import connection.ConnectStaging;
import model.Province;

public class ProvinceDAO {
	public static int addProvince(Province province, Database database) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			int id = -1;
			if (database == Database.Staging) {
				connection = ConnectStaging.getInstance().getConnection();
			} else {
				connection = ConnectDW.getInstance().getConnection();
			}
			connection.setAutoCommit(false);
			String sql = "INSERT INTO province_dim(name) values(?)";
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, province.getName());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			connection.commit();
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return -1;
	}

	public static Province getProvince(int id, Database database) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			if (database == Database.Staging) {
				connection = ConnectStaging.getInstance().getConnection();
			} else {
				connection = ConnectDW.getInstance().getConnection();
			}
			String sql = "SELECT id_pro, name from province_dim where id_pro = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Province(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return null;
	}

	public static Province getProvince(String name, Database database) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			if (database == Database.Staging) {
				connection = ConnectStaging.getInstance().getConnection();
			} else {
				connection = ConnectDW.getInstance().getConnection();
			}
			String sql = "SELECT id_pro, name from province_dim where name = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Province(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return null;
	}
}
