package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectStaging;
import model.Lottery;
import model.Prize;
import model.Result;

public class ResultDAO {
	public static int addResult(Result result) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			int id = -1;
			connection = ConnectStaging.getInstance().getConnection();
			connection.setAutoCommit(false);
			String sql = "INSERT INTO result(id_lot, id_pri, result) values(?,?,?)";
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, result.getLottery().getIdLot());
			ps.setInt(2, result.getPrize().getIdPri());
			ps.setString(3, result.getResult());
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

	public static Result getResult(String idLot, int idPrize) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectStaging.getInstance().getConnection();
			String sql = "SELECT * from result where id_lot = ? and id_pri = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, idLot);
			ps.setInt(2, idPrize);
			rs = ps.executeQuery();
			while (rs.next()) {
				int idRe = rs.getInt(1);
				Lottery lottery = LotteryDAO.getLottery(rs.getString(2));
				Prize prize = PrizeDAO.getPrize(rs.getInt(3));
				String results = rs.getString(4);
				return new Result(idRe, lottery, prize, results);
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
