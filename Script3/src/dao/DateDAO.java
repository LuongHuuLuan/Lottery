package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectDW;
import connection.ConnectStaging;
import model.DateLottery;

public class DateDAO {
	// add 1 row to the table date_dim in DataWH
	public static boolean addDateToDaWH(String fullDate, String day, int date, int month, int year) {
		try {
			Connection connect = ConnectDW.getInstance().getConnection();
			String sql = "INSERT INTO date_dim(full_date, day, date, month, year) values(?,?,?,?,?)";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, fullDate);
			ps.setString(2, day);
			ps.setInt(3, date);
			ps.setInt(4, month);
			ps.setInt(5, year);
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

	// get 1 row in table datesource_dim of Staging
	public static DateLottery getDateInStaging(int id) {
		try {
			Connection connect = ConnectStaging.getInstance().getConnection();
			String sql = "SELECT * from date_dim where id_date = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				return new DateLottery(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getInt(4), resultset.getInt(5), resultset.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	// get 1 row in table datesource_dim of DataWH
	public static DateLottery getDateInDataWH(int date, int month, int year) {
		try {
			Connection connect = ConnectDW.getInstance().getConnection();
			String sql = "SELECT * from date_dim where date = ? and month = ? and year = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setInt(1, date);
			preparedStatement.setInt(2, month);
			preparedStatement.setInt(3, year);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				return new DateLottery(resultset.getInt(1), resultset.getString(2), resultset.getString(3),
						resultset.getInt(4), resultset.getInt(5), resultset.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new DateLottery(0, "", "", 0, 0, 0);
	}

}
