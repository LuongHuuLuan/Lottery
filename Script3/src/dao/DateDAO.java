package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectDW;
import connection.ConnectStaging;
import model.Date;

public class DateDAO {
	public static int addDate(Date date, Database database) {
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
			String sql = "INSERT INTO date_dim(full_date, day, date, month, year) values(?,?,?,?,?)";
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, date.getFullDate());
			ps.setString(2, date.getDay());
			ps.setInt(3, date.getDate());
			ps.setInt(4, date.getMonth());
			ps.setInt(5, date.getYear());
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

	public static Date getDate(int id, Database database) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			if (database == Database.Staging) {
				connection = ConnectStaging.getInstance().getConnection();
			} else {
				connection = ConnectDW.getInstance().getConnection();
			}

			String sql = "SELECT * from date_dim where id_date = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				int idDate = rs.getInt("id_date");
				String fullDate = rs.getString("full_date");
				String day = rs.getString("day");
				int date = rs.getInt("date");
				int month = rs.getInt("month");
				int year = rs.getInt("year");
				return new Date(idDate, fullDate, day, date, month, year);
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

	public static Date getDate(String fullDate, Database database) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			if (database == Database.Staging) {
				connection = ConnectStaging.getInstance().getConnection();
			} else {
				connection = ConnectDW.getInstance().getConnection();
			}
			String sql = "SELECT * from date_dim where full_date = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, fullDate);
			rs = ps.executeQuery();
			while (rs.next()) {
				int idDate = rs.getInt("id_date");
				String fullD = rs.getString("full_date");
				String day = rs.getString("day");
				int date = rs.getInt("date");
				int month = rs.getInt("month");
				int year = rs.getInt("year");
				return new Date(idDate, fullD, day, date, month, year);
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
