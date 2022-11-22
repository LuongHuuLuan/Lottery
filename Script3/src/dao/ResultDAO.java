package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDW;
import connection.ConnectStaging;
import model.Result;

public class ResultDAO {
	// get 1 row the table result in DataWH
	public static Result getResultInDataWH(int idLot, int idPri, String result) {
		try {
			Connection connect = ConnectDW.getInstance().getConnection();
			String sql = "SELECT * from result where id_lot = ? and id_pri = ? and result = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setInt(1, idLot);
			preparedStatement.setInt(2, idPri);
			preparedStatement.setString(3, result);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				return new Result(resultset.getInt(1), resultset.getInt(2), resultset.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new Result(0, 0, "ERR");
	}

	// get all row the table result in Staging
	public static ArrayList<Result> getAllResultInStaging() {
		ArrayList<Result> results = new ArrayList<Result>();
		try {
			Connection connect = ConnectStaging.getInstance().getConnection();
			String sql = "SELECT * from result";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				results.add(new Result(resultset.getInt(1), resultset.getInt(2), resultset.getString(3)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	// add 1 row the table result in DataWH
	public static boolean addResultInDataWH(int idLot, int idPri, String result, String isDelete, Date update,
			Date expried) {
		try {
			Connection connect = ConnectDW.getInstance().getConnection();
			String sql = "INSERT INTO result(id_lot, id_pri, result ,is_delete, update_date, expried_date) values(?,?,?,?,?,?)";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, idLot);
			ps.setInt(2, idPri);
			ps.setString(3, result);
			ps.setString(4, isDelete);
			ps.setDate(5, update);
			ps.setDate(6, expried);
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
//
//	@SuppressWarnings("deprecation")
//	public static void main(String[] args) {
//		LocalDateTime time = LocalDateTime.now();
//		ResultDAO.addResultInDataWH(1, 2, "85236", "false",
//				new Date(time.getYear(), time.getMonthValue(), time.getDayOfMonth()), new Date(3000, 12, 31));
//	}
}
