package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDW;
import connection.ConnectStaging;
import model.MyDate;
import model.Result;

public class ResultDAO {
	// get 1 row the table result in DataWH
	public static Result getResultInDataWH(int idLot, int idPri) {
		try {
			Connection connect = ConnectDW.getInstance().getConnection();
			String sql = "SELECT * from result where id_lot = ? and id_pri = ? and is_delete = 'FALSE'";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setInt(1, idLot);
			preparedStatement.setInt(2, idPri);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				return new Result(resultset.getInt(1) + "", resultset.getInt(2), resultset.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new Result("ERR", 0, "ERR");
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
				results.add(new Result(resultset.getString(1), resultset.getInt(2), resultset.getString(3)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	// add 1 row the table result in DataWH
	public static boolean addResultInDataWH(int idLot, int idPri, String result) {
		try {
			Connection connect = ConnectDW.getInstance().getConnection();
			String sql = "INSERT INTO result(id_lot, id_pri, result) values(?,?,?)";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, idLot);
			ps.setInt(2, idPri);
			ps.setString(3, result);
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

	// update table file_log isDelete FALSE==>>TRUE
	public static void updateIsDelete(String idLot, int idPri) {
		MyDate myDate = new MyDate();
		try {
			Connection connect = ConnectDW.getInstance().getConnection();
			String sql = "update result set is_delete = 'TRUE',update_date=? where is_delete = 'FALSE' and id_lot=? and id_pri=?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setTimestamp(1, myDate.toTimeStamp());
			preparedStatement.setString(2, idLot);
			preparedStatement.setInt(3, idPri);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MyDate myDate = new MyDate();
		System.out.println(myDate.getYear());
	}
}
