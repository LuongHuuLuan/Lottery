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
import model.Lottery;
import model.Prize;
import model.Result;

public class ResultDAO {
	public static int addResult(Result result, Database database) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql;
			int id = -1;
			if (database == Database.Staging) {
				connection = ConnectStaging.getInstance().getConnection();
				sql = "INSERT INTO result(id_lot, id_pri, result) values(?,?,?)";
			} else {
				connection = ConnectDW.getInstance().getConnection();
				sql = "INSERT INTO result(id_lot, id_pri, result) values(?,?,?)";
			}
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
				int idRe = rs.getInt("id_re");
				Lottery lottery = LotteryDAO.getLottery(rs.getString("id_lot"), database);
				Prize prize = PrizeDAO.getPrize(rs.getInt("id_pri"), database);
				String results = rs.getString("result");
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
				int idRe = rs.getInt(1);
				Lottery lottery = LotteryDAO.getLottery(rs.getString(2), Database.Staging);
				Prize prize = PrizeDAO.getPrize(rs.getInt(3), Database.Staging);
				String res = rs.getString(4);
				Result result = new Result(idRe, lottery, prize, res);
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
