package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectStaging;
import model.Prize;

public class PrizeDAO {
	public static int addPrize(Prize prize) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			int id = -1;
			connection = ConnectStaging.getInstance().getConnection();
			connection.setAutoCommit(false);
			String sql = "INSERT INTO prize_dim(code_pri, name, prize) values(?,?,?)";
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, prize.getCodePri());
			ps.setString(2, prize.getName());
			ps.setDouble(3, prize.getPrize());
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

	public static Prize getPrize(int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectStaging.getInstance().getConnection();
			String sql = "SELECT * from prize_dim where id_pri = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				int idPri = rs.getInt("id_pri");
				String codePri = rs.getString("code_pri");
				String name = rs.getString("name");
				double prize = rs.getDouble("prize");
				return new Prize(idPri, codePri, name, prize);
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

	public static Prize getPrize(String name) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectStaging.getInstance().getConnection();
			String sql = "SELECT * from prize_dim where name = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				int idPri = rs.getInt("id_pri");
				String codePri = rs.getString("code_pri");
				double prize = rs.getDouble("prize");
				return new Prize(idPri, codePri, name, prize);
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
