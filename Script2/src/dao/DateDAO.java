package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectStaging;
import model.Date;

public class DateDAO {
	public static int addDate(Date date) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			int id = -1;
			connection = ConnectStaging.getInstance().getConnection();
			connection.setAutoCommit(false);
			String sql = "INSERT INTO date_dim(full_date, short_date, day, date, month, year) values(?,?,?,?,?,?)";
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, date.getFullDate());
			ps.setString(2, date.getShortDate());
			ps.setString(3, date.getDay());
			ps.setInt(4, date.getDate());
			ps.setInt(5, date.getMonth());
			ps.setInt(6, date.getYear());
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

	public static Date getDate(int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectStaging.getInstance().getConnection();
			String sql = "SELECT * from date_dim where id_date = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				int idDate = rs.getInt("id_date");
				String fullDate = rs.getString("full_date");
				String shortDate = rs.getString("short_date");
				String day = rs.getString("day");
				int date = rs.getInt("date");
				int month = rs.getInt("month");
				int year = rs.getInt("year");
				return new Date(idDate, fullDate, shortDate, day, date, month, year);
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

	public static Date getDate(String fullDate) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectStaging.getInstance().getConnection();
			String sql = "SELECT * from date_dim where full_date = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, fullDate);
			rs = ps.executeQuery();
			while (rs.next()) {
				int idDate = rs.getInt("id_date");
				String fullD = rs.getString("full_date");
				String shortDate = rs.getString("short_date");
				String day = rs.getString("day");
				int date = rs.getInt("date");
				int month = rs.getInt("month");
				int year = rs.getInt("year");
				return new Date(idDate, fullD, shortDate, day, date, month, year);
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
