package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectDW;
import connection.ConnectStaging;
import model.Prize;

public class PrizeDAO {
	public static int addPrize(Prize prize, Database database) {
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
			String sql = "INSERT INTO prize_dim(name, prize) values(?,?)";
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, prize.getName());
			ps.setDouble(2, prize.getPrize());
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

	public static Prize getPrize(int id, Database database) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			if (database == Database.Staging) {
				connection = ConnectStaging.getInstance().getConnection();
			} else {
				connection = ConnectDW.getInstance().getConnection();
			}
			String sql = "SELECT * from prize_dim where id_pri = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Prize(rs.getInt("id_pri"), rs.getString("name"), rs.getDouble("prize"));
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

	public static Prize getPrize(String name, Database database) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			if (database == Database.Staging) {
				connection = ConnectStaging.getInstance().getConnection();
			} else {
				connection = ConnectDW.getInstance().getConnection();
			}
			String sql = "SELECT * from prize_dim where name = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Prize(rs.getInt("id_pri"), rs.getString("name"), rs.getDouble("prize"));
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

	public static List<Prize> getAllPrizeFromStaging() {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Prize> prizes = new ArrayList<Prize>();
		try {
			connection = ConnectStaging.getInstance().getConnection();
			String sql = "SELECT * from prize_dim";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Prize prize = new Prize(rs.getInt("id_pri"), rs.getString("name"), rs.getDouble("prize"));
				prizes.add(prize);
			}
			return prizes;
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
