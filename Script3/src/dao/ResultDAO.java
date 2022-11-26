package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectDW;
import connection.ConnectStaging;
import model.Lottery;
import model.Prize;
import model.Result;

public class ResultDAO {
	public static void addResult(Result result, Database database) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql;
			if (database == Database.Staging) {
				connection = ConnectStaging.getInstance().getConnection();
				sql = "INSERT INTO result(id_lot, id_pri, result) values(?,?,?)";
			} else {
				connection = ConnectDW.getInstance().getConnection();
				sql = "INSERT INTO result(id_lot, id_pri, result) values(?,?,?)";
			}
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			if (database == Database.Staging) {
				ps.setString(1, result.getLottery().getNkIdLot());
				ps.setInt(2, result.getPrize().getIdPri());
				ps.setString(3, result.getResult());
			} else {
				ps.setInt(1, result.getLottery().getIdLot());
				ps.setInt(2, result.getPrize().getIdPri());
				ps.setString(3, result.getResult());
			}
			ps.executeUpdate();
			connection.commit();
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
	}

	public static Result getResult(String idLot, int idPrize, Database database) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql;
			if (database == Database.Staging) {
				connection = ConnectStaging.getInstance().getConnection();
				sql = "SELECT * from result where id_lot = ? and id_pri = ?";
			} else {
				connection = ConnectDW.getInstance().getConnection();
				sql = "SELECT * from result where id_lot = ? and id_pri = ? and is_delete = 'FALSE'";
			}
			ps = connection.prepareStatement(sql);
			ps.setString(1, idLot);
			ps.setInt(2, idPrize);
			rs = ps.executeQuery();
			while (rs.next()) {
				Lottery lottery = LotteryDAO.getLottery(rs.getString(1), database);
				Prize prize = PrizeDAO.getPrize(rs.getInt(2), database);
				String results = rs.getString(3);
				return new Result(lottery, prize, results);
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

	public static List<Result> getAllResultsInStaging(String nkIdLot) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Result> results = new ArrayList<Result>();
		try {
			String sql = "SELECT * from result where id_lot = ?";
			connection = ConnectStaging.getInstance().getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, nkIdLot);
			rs = ps.executeQuery();
			while (rs.next()) {
				Lottery lottery = LotteryDAO.getLottery(rs.getString(1), Database.Staging);
				Prize prize = PrizeDAO.getPrize(rs.getInt(2), Database.Staging);
				String res = rs.getString(3);
				Result result = new Result(lottery, prize, res);
				results.add(result);
			}
			return results;
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
